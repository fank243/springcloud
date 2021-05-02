package com.fank243.cloud.system.controller;

import cn.hutool.core.util.StrUtil;
import com.fank243.cloud.common.core.constant.UserConstants;
import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.common.core.utils.SecurityUtils;
import com.fank243.cloud.common.core.utils.StringUtils;
import com.fank243.cloud.common.core.web.controller.BaseController;
import com.fank243.cloud.common.log.annotation.Log;
import com.fank243.cloud.common.log.enums.BusinessType;
import com.fank243.cloud.common.security.annotation.PreAuthorize;
import com.fank243.cloud.system.api.domain.SysDept;
import com.fank243.cloud.system.service.ISysDeptService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 部门信息
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController extends BaseController {
    @Autowired
    private ISysDeptService deptService;

    /**
     * 获取部门列表
     */
    @PreAuthorize(hasPermi = "system:dept:list")
    @GetMapping("/list")
    public ResultInfo<?> list(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return ResultInfo.ok(depts);
    }

    /**
     * 查询部门列表（排除节点）
     */
    @PreAuthorize(hasPermi = "system:dept:list")
    @GetMapping("/list/exclude/{deptId}")
    public ResultInfo<?> excludeChild(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        depts.removeIf(d -> d.getDeptId().intValue() == deptId
            || ArrayUtils.contains(StrUtil.split(d.getAncestors(), ","), deptId + ""));
        return ResultInfo.ok(depts);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @PreAuthorize(hasPermi = "system:dept:query")
    @GetMapping(value = "/{deptId}")
    public ResultInfo<?> getInfo(@PathVariable Long deptId) {
        return ResultInfo.ok(deptService.selectDeptById(deptId));
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public ResultInfo<?> treeselect(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return ResultInfo.ok(deptService.buildDeptTreeSelect(depts));
    }

    /**
     * 加载对应角色部门列表树
     */
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public ResultInfo<?> roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        Map<String, Object> map = new HashMap<>(2);
        map.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        map.put("depts", deptService.buildDeptTreeSelect(depts));
        return ResultInfo.ok(map);
    }

    /**
     * 新增部门
     */
    @PreAuthorize(hasPermi = "system:dept:add")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultInfo<?> add(@Validated @RequestBody SysDept dept) {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return ResultInfo.fail("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @PreAuthorize(hasPermi = "system:dept:edit")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultInfo<?> edit(@Validated @RequestBody SysDept dept) {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return ResultInfo.fail("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        } else if (dept.getParentId().equals(dept.getDeptId())) {
            return ResultInfo.fail("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        } else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus())
            && deptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0) {
            return ResultInfo.fail("该部门包含未停用的子部门！");
        }
        dept.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除部门
     */
    @PreAuthorize(hasPermi = "system:dept:remove")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public ResultInfo<?> remove(@PathVariable Long deptId) {
        if (deptService.hasChildByDeptId(deptId)) {
            return ResultInfo.fail("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId)) {
            return ResultInfo.fail("部门存在用户,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }
}

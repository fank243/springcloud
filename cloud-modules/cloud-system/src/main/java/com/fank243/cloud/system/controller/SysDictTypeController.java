package com.fank243.cloud.system.controller;

import com.fank243.cloud.common.core.constant.UserConstants;
import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.common.core.utils.SecurityUtils;
import com.fank243.cloud.common.core.utils.poi.ExcelUtil;
import com.fank243.cloud.common.core.web.controller.BaseController;
import com.fank243.cloud.common.core.web.page.TableDataInfo;
import com.fank243.cloud.common.log.annotation.Log;
import com.fank243.cloud.common.log.enums.BusinessType;
import com.fank243.cloud.common.security.annotation.PreAuthorize;
import com.fank243.cloud.system.domain.SysDictType;
import com.fank243.cloud.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 数据字典信息
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@RestController
@RequestMapping("/dict/type")
public class SysDictTypeController extends BaseController {
    @Autowired
    private ISysDictTypeService dictTypeService;

    @PreAuthorize(hasPermi = "system:dict:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDictType dictType) {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:dict:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictType dictType) throws IOException {
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        ExcelUtil<SysDictType> util = new ExcelUtil<>(SysDictType.class);
        util.exportExcel(response, list, "字典类型");
    }

    /**
     * 查询字典类型详细
     */
    @PreAuthorize(hasPermi = "system:dict:query")
    @GetMapping(value = "/{dictId}")
    public ResultInfo<?> getInfo(@PathVariable Long dictId) {
        return ResultInfo.ok(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:add")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultInfo<?> add(@Validated @RequestBody SysDictType dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return ResultInfo.fail("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(SecurityUtils.getUsername());
        return ResultInfo.ok(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:edit")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultInfo<?> edit(@Validated @RequestBody SysDictType dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return ResultInfo.fail("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(SecurityUtils.getUsername());
        return ResultInfo.ok(dictTypeService.updateDictType(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictIds}")
    public ResultInfo<?> remove(@PathVariable Long[] dictIds) {
        return ResultInfo.ok(dictTypeService.deleteDictTypeByIds(dictIds));
    }

    /**
     * 清空缓存
     */
    @PreAuthorize(hasPermi = "system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clearCache")
    public ResultInfo<?> clearCache() {
        dictTypeService.clearCache();
        return ResultInfo.ok();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public ResultInfo<?> optionselect() {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return ResultInfo.ok(dictTypes);
    }
}

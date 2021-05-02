package com.fank243.cloud.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fank243.cloud.common.core.domain.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fank243.cloud.common.core.constant.Constants;
import com.fank243.cloud.common.core.constant.UserConstants;
import com.fank243.cloud.common.core.utils.SecurityUtils;
import com.fank243.cloud.common.core.utils.StringUtils;
import com.fank243.cloud.common.core.web.controller.BaseController;
import com.fank243.cloud.common.log.annotation.Log;
import com.fank243.cloud.common.log.enums.BusinessType;
import com.fank243.cloud.common.security.annotation.PreAuthorize;
import com.fank243.cloud.system.domain.SysMenu;
import com.fank243.cloud.system.service.ISysMenuService;

/**
 * 菜单信息
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取菜单列表
     */
    @PreAuthorize(hasPermi = "system:menu:list")
    @GetMapping("/list")
    public ResultInfo<?> list(SysMenu menu) {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        return ResultInfo.ok(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @PreAuthorize(hasPermi = "system:menu:query")
    @GetMapping(value = "/{menuId}")
    public ResultInfo<?> getInfo(@PathVariable Long menuId) {
        return ResultInfo.ok(menuService.selectMenuById(menuId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public ResultInfo<?> treeselect(SysMenu menu) {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        return ResultInfo.ok(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public ResultInfo<?> roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(userId);
        Map<String, Object> map = new HashMap<>(2);
        map.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        map.put("menus", menuService.buildMenuTreeSelect(menus));
        return ResultInfo.ok(map);
    }

    /**
     * 新增菜单
     */
    @PreAuthorize(hasPermi = "system:menu:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultInfo<?> add(@Validated @RequestBody SysMenu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return ResultInfo.fail("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame())
            && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            return ResultInfo.fail("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        menu.setCreateBy(SecurityUtils.getUsername());
        return ResultInfo.ok(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @PreAuthorize(hasPermi = "system:menu:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultInfo<?> edit(@Validated @RequestBody SysMenu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return ResultInfo.fail("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame())
            && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            return ResultInfo.fail("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        } else if (menu.getMenuId().equals(menu.getParentId())) {
            return ResultInfo.fail("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(SecurityUtils.getUsername());
        return ResultInfo.ok(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @PreAuthorize(hasPermi = "system:menu:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public ResultInfo<?> remove(@PathVariable("menuId") Long menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return ResultInfo.fail("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId)) {
            return ResultInfo.fail("菜单已分配,不允许删除");
        }
        return ResultInfo.ok(menuService.deleteMenuById(menuId));
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public ResultInfo<?> getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return ResultInfo.ok(menuService.buildMenus(menus));
    }
}
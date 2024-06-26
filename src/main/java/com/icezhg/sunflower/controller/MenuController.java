package com.icezhg.sunflower.controller;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.MenuInfo;
import com.icezhg.sunflower.pojo.MenuTree;
import com.icezhg.sunflower.pojo.RoleMenuTree;
import com.icezhg.sunflower.pojo.query.MenuQuery;
import com.icezhg.sunflower.service.MenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhongjibing on 2022/09/07.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{menuId}")
    @PreAuthorize("hasAuthority('system:menu:query')")
    public MenuInfo get(@PathVariable Integer menuId) {
        return menuService.findMenu(menuId);
    }

    @DeleteMapping("/{menuId}")
    @PreAuthorize("hasAuthority('system:menu:delete')")
    @Operation(title = "system menus deletion", type = OperationType.DELETE)
    public int delete(@PathVariable Integer menuId) {
        return menuService.delete(menuId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('system:menu:add')")
    @Operation(title = "system menus addition", type = OperationType.INSERT)
    public MenuInfo add(@RequestBody MenuInfo menuInfo) {
        return menuService.save(menuInfo);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('system:menu:edit')")
    @Operation(title = "system menus modification", type = OperationType.UPDATE)
    public MenuInfo edit(@RequestBody MenuInfo menuInfo) {
        return menuService.update(menuInfo);
    }


    @GetMapping("/tree")
    @PreAuthorize("hasAnyAuthority('system:role:add', 'system:role:edit')")
    public List<MenuTree> roleFilteredMenuTree() {
        return menuService.buildMenuTreeSelect();
    }

    @GetMapping("/roleMenuTree/{roleId}")
    @PreAuthorize("hasAnyAuthority('system:role:add', 'system:role:edit')")
    public RoleMenuTree roleMenuTree(@PathVariable Integer roleId) {
        return menuService.buildRoleMenuTreeSelect(roleId);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:menu:list')")
    public Object list(MenuQuery query) {
        return menuService.list(query);
    }
}

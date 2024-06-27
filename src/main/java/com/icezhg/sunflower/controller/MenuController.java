package com.icezhg.sunflower.controller;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.MenuInfo;
import com.icezhg.sunflower.pojo.MenuTree;
import com.icezhg.sunflower.pojo.RoleMenuTree;
import com.icezhg.sunflower.pojo.query.MenuQuery;
import com.icezhg.sunflower.service.MenuService;
import org.springframework.security.access.annotation.Secured;
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
    @Secured(Authority.System.Menu.QUERY)
    public MenuInfo get(@PathVariable Integer menuId) {
        return menuService.findMenu(menuId);
    }

    @DeleteMapping("/{menuId}")
    @Secured(Authority.System.Menu.DELETE)
    @Operation(title = "system menus deletion", type = OperationType.DELETE)
    public int delete(@PathVariable Integer menuId) {
        return menuService.delete(menuId);
    }

    @PostMapping
    @Secured(Authority.System.Menu.ADD)
    @Operation(title = "system menus addition", type = OperationType.INSERT)
    public MenuInfo add(@RequestBody MenuInfo menuInfo) {
        return menuService.save(menuInfo);
    }

    @PutMapping
    @Secured(Authority.System.Menu.EDIT)
    @Operation(title = "system menus modification", type = OperationType.UPDATE)
    public MenuInfo edit(@RequestBody MenuInfo menuInfo) {
        return menuService.update(menuInfo);
    }


    @GetMapping("/tree")
    @Secured({Authority.System.Role.ADD, Authority.System.Role.EDIT})
    public List<MenuTree> roleFilteredMenuTree() {
        return menuService.buildMenuTreeSelect();
    }

    @GetMapping("/roleMenuTree/{roleId}")
    @Secured({Authority.System.Role.ADD, Authority.System.Role.EDIT})
    public RoleMenuTree roleMenuTree(@PathVariable Integer roleId) {
        return menuService.buildRoleMenuTreeSelect(roleId);
    }

    @GetMapping("/list")
    @Secured(Authority.System.Menu.QUERY)
    public Object list(MenuQuery query) {
        return menuService.list(query);
    }
}

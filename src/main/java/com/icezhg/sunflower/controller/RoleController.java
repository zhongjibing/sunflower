package com.icezhg.sunflower.controller;

import com.icezhg.commons.exception.ErrorCodeException;
import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.RoleInfo;
import com.icezhg.sunflower.pojo.query.NameQuery;
import com.icezhg.sunflower.pojo.query.RoleQuery;
import com.icezhg.sunflower.service.RoleService;
import com.icezhg.sunflower.service.UserRoleService;
import org.springframework.validation.annotation.Validated;
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
 * Created by zhongjibing on 2022/09/04.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    private final UserRoleService userRoleService;

    public RoleController(RoleService roleService, UserRoleService userRoleService) {
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @PostMapping
    @Operation(title = "roles addition", type = OperationType.INSERT)
    public RoleInfo add(@Validated @RequestBody RoleInfo role) {
        if (!roleService.checkUnique(role)) {
            throw new ErrorCodeException("", "role name is already exists");
        }
        return roleService.save(role);
    }

    @PutMapping
    @Operation(title = "roles modification", type = OperationType.UPDATE)
    public RoleInfo edit(@Validated @RequestBody RoleInfo role) {
        if (!roleService.checkUnique(role)) {
            throw new ErrorCodeException("", "role name is already exists");
        }
        return roleService.update(role);
    }

    @DeleteMapping
    @Operation(title = "roles deletion", type = OperationType.DELETE)
    public int delete(@RequestBody List<Integer> roleIds) {
        return roleService.deleteRoles(roleIds);
    }

    @GetMapping("/list")
    public PageResult list(RoleQuery query) {
        return new PageResult(roleService.count(query), roleService.find(query));
    }

    @GetMapping("/{roleId}")
    public RoleInfo get(@PathVariable Integer roleId) {
        return roleService.findRoleInfo(roleId);
    }

    @PutMapping("/changeStatus")
    @Operation(title = "role status change", type = OperationType.UPDATE)
    public int changeStatus(@RequestBody RoleInfo roleInfo) {
        return roleService.changeStatus(roleInfo);
    }

    @GetMapping("/{roleId}/allocatedUsers")
    public PageResult allocatedUsers(@PathVariable Integer roleId, NameQuery nameQuery) {
        return userRoleService.listAllocatedUsers(roleId, nameQuery);
    }

    @GetMapping("/{roleId}/unallocatedUsers")
    public PageResult unallocatedUsers(@PathVariable Integer roleId, NameQuery nameQuery) {
        return userRoleService.listUnallocatedUsers(roleId, nameQuery);
    }

    @PostMapping("/{roleId}/authUser")
    @Operation(title = "grant the specified role to the specified users", type = OperationType.GRANT)
    public void authUser(@PathVariable Integer roleId, @RequestBody List<Long> userIds) {
        userRoleService.authUser(roleId, userIds);
    }

    @DeleteMapping("/{roleId}/authUser")
    @Operation(title = "revoke the specified role from the specified users", type = OperationType.REVOKE)
    public void authUserCancel(@PathVariable Integer roleId, @RequestBody List<Long> userIds) {
        userRoleService.cancelAuth(roleId, userIds);
    }

}

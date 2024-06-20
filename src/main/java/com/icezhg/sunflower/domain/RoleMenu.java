package com.icezhg.sunflower.domain;

/**
 * 角色和菜单关联表
 */
public record RoleMenu(
        int roleId,
        int menuId
) {}

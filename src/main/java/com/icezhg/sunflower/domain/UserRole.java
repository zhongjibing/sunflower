package com.icezhg.sunflower.domain;

/**
 * 用户和角色关联表
 *
 * @TableName sys_user_role
 */
public record UserRole(
        Long userId,
        Integer roleId
) {
}

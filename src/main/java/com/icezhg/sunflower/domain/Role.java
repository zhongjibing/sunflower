package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色信息表
 *
 * @TableName sys_role
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {


    /**
     * 角色id
     */
    private Integer id;

    /**
     * 权限字符
     */
    private String name;

    /**
     * 角色名称
     */
    private String roleKey;

    /**
     * 显示顺序
     */
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private Integer dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;
}

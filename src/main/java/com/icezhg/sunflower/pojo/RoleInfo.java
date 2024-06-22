package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * Created by zhongjibing on 2022/09/07.
 */
@Data
public class RoleInfo {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    @NotBlank
    @Size(max = 16)
    private String name;

    /**
     * 权限字符
     */
    @NotBlank
    @Size(max = 16)
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
     * 菜单树选择项是否关联显示
     */
    private boolean menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示
     */
    private boolean deptCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单id
     */
    private List<Integer> menuIds;

}

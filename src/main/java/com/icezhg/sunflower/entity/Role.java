package com.icezhg.sunflower.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表
 *
 * @TableName sys_role
 */
@Entity
@Table(name = "sys_role")
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = -8780728681348695870L;

    /**
     * 角色id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限字符
     */
    private String name;

    /**
     * 角色名称
     */
    @Column(name = "role_key")
    private String roleKey;

    /**
     * 显示顺序
     */
    @Column(name = "role_sort")
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @Column(name = "data_scope")
    private Integer dataScope;

    /**
     * 菜单树选择项是否关联显示
     */
    @Column(name = "menu_check_strictly")
    private Integer menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示
     */
    @Column(name = "dept_check_strictly")
    private Integer deptCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    public Integer getDataScope() {
        return dataScope;
    }

    public void setDataScope(Integer dataScope) {
        this.dataScope = dataScope;
    }

    public Integer getMenuCheckStrictly() {
        return menuCheckStrictly;
    }

    public void setMenuCheckStrictly(Integer menuCheckStrictly) {
        this.menuCheckStrictly = menuCheckStrictly;
    }

    public Integer getDeptCheckStrictly() {
        return deptCheckStrictly;
    }

    public void setDeptCheckStrictly(Integer deptCheckStrictly) {
        this.deptCheckStrictly = deptCheckStrictly;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

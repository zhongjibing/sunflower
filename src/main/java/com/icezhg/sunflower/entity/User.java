package com.icezhg.sunflower.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户信息表
 *
 * @TableName sys_user
 */
@Entity
@Table(name = "sys_user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -6524017838309913144L;

    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    private String username;


    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户性别: 0.女, 1.男
     */
    private String gender;

    /**
     * 生日
     */
    private String birthdate;

    /**
     * 邮件
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 有效时间
     */
    private Date deadline;

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
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 密码更新时间
     */
    @Column(name = "credentials_update_time")
    private Date credentialsUpdateTime;

    /**
     * 是否默认用户: 0.否, 1.是
     */
    @Column(name = "default_user")
    private Integer defaultUser;

    /**
     * 是否存档: 0.否, 1.是
     */
    private Integer archived;

    /**
     * 是否锁定: 0.否, 1.是
     */
    @Column(name = "account_locked")
    private Integer accountLocked;

    /**
     * 备注
     */
    private String remark;

    public boolean accountNonExpired() {
        return deadline == null || deadline.getTime() > Calendar.getInstance().getTimeInMillis();
    }

    public boolean accountNonLocked() {
        return accountLocked != null && accountLocked == 0;
    }

    public boolean credentialsNonExpired() {
        Date credentialsCreateTime = credentialsUpdateTime != null ? credentialsUpdateTime : createTime;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        return credentialsCreateTime.getTime() > calendar.getTimeInMillis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCredentialsUpdateTime() {
        return credentialsUpdateTime;
    }

    public void setCredentialsUpdateTime(Date credentialsUpdateTime) {
        this.credentialsUpdateTime = credentialsUpdateTime;
    }

    public Integer getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(Integer defaultUser) {
        this.defaultUser = defaultUser;
    }

    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    public Integer getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Integer accountLocked) {
        this.accountLocked = accountLocked;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

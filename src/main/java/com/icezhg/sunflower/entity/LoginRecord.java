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
 * 系统访问记录
 *
 * @TableName t_login_record
 */
@Entity
@Table(name = "t_login_record")
public class LoginRecord implements Serializable {
    @Serial
    private static final long serialVersionUID = 2123132321108002735L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录IP地址
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 登录地点
     */
    @Column(name = "login_location")
    private String loginLocation;

    /**
     * 代理字符串
     */
    private String agent;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录状态（0成功 1失败）
     */
    private String status;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 访问时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 登录IP地址
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 登录IP地址
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 登录地点
     */
    public String getLoginLocation() {
        return loginLocation;
    }

    /**
     * 登录地点
     */
    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    /**
     * 代理字符串
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 代理字符串
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * 浏览器类型
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * 浏览器类型
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * 操作系统
     */
    public String getOs() {
        return os;
    }

    /**
     * 操作系统
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * 登录状态（0成功 1失败）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 登录状态（0成功 1失败）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 提示消息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 提示消息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 访问时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 访问时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * 系统访问记录
 *
 * @TableName t_login_record
 */
@Data
public class LoginRecord {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录IP地址
     */
    private String loginIp;

    /**
     * 登录地点
     */
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
    private Date loginTime;

    /**
     * 登录方式
     */
    private Integer loginMethod;

}

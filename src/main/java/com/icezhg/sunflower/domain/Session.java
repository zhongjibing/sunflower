package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * @TableName sys_session
 */
@Data
public class Session {
    /**
     * 主键id
     */
    private String id;

    /**
     * 会话id
     */
    private String sessionId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 登录位置
     */
    private String loginLocation;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 最后访问时间
     */
    private Date lastAccessedTime;

    /**
     * 代理字符串
     */
    private String agent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
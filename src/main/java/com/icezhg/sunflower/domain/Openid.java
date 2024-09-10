package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * 微信账号
 *
 * @TableName sys_openid
 */
@Data
public class Openid {
    /**
     * id
     */
    private Long id;

    /**
     * 微信id
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 状态
     */
    private String status;

    /**
     * 分享码
     */
    private String code;

    /**
     * uid
     */
    private String uid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

}

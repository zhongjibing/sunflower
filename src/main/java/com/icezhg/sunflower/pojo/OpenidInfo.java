package com.icezhg.sunflower.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/07/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OpenidInfo extends OpenidUser {

    /**
     * 头像
     */
    private String avatar;

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
     * 最后登录时间
     */
    private Date lastLoginTime;
}

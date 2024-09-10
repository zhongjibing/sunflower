package com.icezhg.sunflower.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/07/18.
 */
@Data
public class OpenidInfo {

    /**
     * id
     */
    private Long id;

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

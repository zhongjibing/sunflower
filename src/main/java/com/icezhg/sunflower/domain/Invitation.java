package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * @TableName t_invitation
 */
@Data
public class Invitation {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * openid
     */
    private String openid;

    /**
     * 邀请码
     */
    private String code;

    /**
     * 邀请人邀请码
     */
    private String inviterCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
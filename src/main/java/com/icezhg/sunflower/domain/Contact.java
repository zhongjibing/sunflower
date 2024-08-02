package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/08/03
 */
@Data
public class Contact {
    /**
     * 主键
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 微信用户id
     */
    private String openid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}

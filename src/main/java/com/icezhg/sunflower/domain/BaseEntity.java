package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhongjibing on 2022/09/07.
 */
@Data
public abstract class BaseEntity {
    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;
}

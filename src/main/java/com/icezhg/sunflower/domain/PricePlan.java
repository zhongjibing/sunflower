package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * 价格计划
 */
@Data
public class PricePlan {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 日期
     */
    private Date date;

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 资源类型
     */
    private Integer type;

    /**
     * 价格
     */
    private Long price;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}

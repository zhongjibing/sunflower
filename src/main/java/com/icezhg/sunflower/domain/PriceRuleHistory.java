package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * @TableName t_price_rule_history
 */
@Data
public class PriceRuleHistory {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 名称
     */
    private String name;

    /**
     * 客户标签
     */
    private Object tagId;

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 资源类型
     */
    private Integer type;

    /**
     * 规则详细
     */
    private String detail;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 备注
     */
    private String remark;

}

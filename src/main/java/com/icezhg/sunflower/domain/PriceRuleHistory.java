package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @TableName t_price_rule_history
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PriceRuleHistory extends BaseEntity {
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
     * 操作时间
     */
    private Date operateTime;

}

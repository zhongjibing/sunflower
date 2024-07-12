package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 价格规则
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PriceRule extends BaseEntity {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 客户标签
     */
    private Integer tagId;

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

}

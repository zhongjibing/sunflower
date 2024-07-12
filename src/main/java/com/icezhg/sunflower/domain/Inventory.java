package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName t_inventory
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Inventory extends BaseEntity {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 是否删除
     */
    private Integer deleted;

}

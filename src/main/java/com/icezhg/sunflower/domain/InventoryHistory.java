package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @TableName t_inventory_history
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InventoryHistory extends BaseEntity {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 库存id
     */
    private Long inventoryId;

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

    /**
     * 操作时间
     */
    private Date operateTime;

}

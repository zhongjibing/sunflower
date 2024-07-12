package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * @TableName t_inventory_plan
 */
@Data
public class InventoryPlan {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 库存id
     */
    private Long inventoryId;

    /**
     * 日期
     */
    private Date date;

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 总数
     */
    private Integer total;

    /**
     * 使用数
     */
    private Integer used;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
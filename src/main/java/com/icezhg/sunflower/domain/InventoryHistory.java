package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * @TableName t_inventory_history
 */
@Data
public class InventoryHistory {
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
    private Object number;

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
     * 更新时间
     */
    private String updateBy;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作时间
     */
    private Date operateTime;

}

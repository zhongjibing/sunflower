package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * @TableName t_inventory
 */
@Data
public class Inventory {
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

}
package com.icezhg.sunflower.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Data
public class InventoryDetail {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源类型
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

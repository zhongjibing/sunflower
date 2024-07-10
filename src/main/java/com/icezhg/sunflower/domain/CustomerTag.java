package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * 客户标签
 */
@Data
public class CustomerTag {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 样式类名
     */
    private String styleClass;

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

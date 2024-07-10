package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName t_resource
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Resource extends BaseEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 状态
     */
    private String status;

    /**
     * 是否删除
     */
    private Integer deleted;

}

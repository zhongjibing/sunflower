package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型表
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictType extends BaseEntity {
    /**
     * 字典主键
     */
    private Integer id;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    private String status;
}

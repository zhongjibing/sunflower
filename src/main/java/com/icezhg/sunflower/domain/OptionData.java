package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 选项数据表
 *
 * @TableName t_option_data
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OptionData extends BaseEntity {
    /**
     * 选项编码
     */
    private Integer id;

    /**
     * 选项排序
     */
    private Integer optionSort;

    /**
     * 选项标签
     */
    private String optionLabel;

    /**
     * 选项键值
     */
    private String optionValue;

    /**
     * 选项键值类型
     */
    private String optionValueType;

    /**
     * 选项类型
     */
    private String optionType;

    /**
     * 样式属性（其他样式扩展）
     */
    private String cssClass;

    /**
     * 表格回显样式
     */
    private String listClass;

    /**
     * 是否默认（Y是 N否）
     */
    private String isDefault;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

}
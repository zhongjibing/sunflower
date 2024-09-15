package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 选项类型表
 *
 * @TableName t_option_type
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OptionType extends BaseEntity {
    /**
     * 选项主键
     */
    private Integer id;

    /**
     * 选项名称
     */
    private String optionName;

    /**
     * 选项类型
     */
    private String optionType;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

}
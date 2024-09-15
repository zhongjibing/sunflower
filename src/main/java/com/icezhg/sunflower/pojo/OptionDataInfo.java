package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Created by zhongjibing on 2022/09/17.
 */
@Data
public class OptionDataInfo {
    /**
     * 字典编码
     */
    private Integer id;

    /**
     * 字典排序
     */
    private Integer optionSort;

    /**
     * 字典标签
     */
    @NotBlank
    @Size(max = 32)
    private String optionLabel;

    /**
     * 字典键值
     */
    @NotBlank
    @Size(max = 64)
    private String optionValue;

    /**
     * 字典键值类型
     */
    private String optionValueType;

    /**
     * 字典类型
     */
    @NotBlank
    @Size(max = 32)
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
    /**
     * 备注
     */
    private String remark;
}

package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


/**
 * Created by zhongjibing on 2022/09/16.
 */
@Data
public class OptionTypeInfo {
    /**
     * 字典主键
     */
    private Integer id;

    /**
     * 字典名称
     */
    @NotBlank
    @Size(max = 32)
    private String optionName;

    /**
     * 字典类型
     */
    @NotBlank
    @Size(max = 32)
    private String optionType;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;
}

package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@Data
public class PriceRuleInfo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 客户标签
     */
    @NotNull
    private Integer tagId;

    /**
     * 资源id
     */
    @NotNull
    private Long resourceId;

    /**
     * 规则详细
     */
    @NotBlank
    @Size(max = 1024)
    private String detail;

    /**
     * 备注
     */
    @Size(max = 128)
    private String remark;
}

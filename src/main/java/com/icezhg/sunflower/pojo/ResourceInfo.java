package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@Data
public class ResourceInfo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    @NotBlank
    @Size(max = 128)
    private String name;

    /**
     * 描述
     */
    @Size(max = 256)
    private String description;

    /**
     * 规则详细
     */
    private String detail;

    /**
     * 备注
     */
    private String remark;

}

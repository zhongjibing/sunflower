package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.Min;
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
    private String id;

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
     * 数量
     */
    @Min(0)
    private Integer number;

    /**
     * 备注
     */
    private String remark;
}

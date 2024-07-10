package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Created by zhongjibing on 2023/07/10.
 */
@Data
public class CustomerTagInfo {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 名称
     */
    @NotBlank
    @Size(max = 8)
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
     * 备注
     */
    private String remark;
}

package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Data
public class InventoryInfo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 资源id
     */
    @NotNull
    private Long resourceId;

    /**
     * 数量
     */
    @NotNull
    @Min(0)
    private Integer number;

    /**
     * 备注
     */
    @Size(max = 128)
    private String remark;
}

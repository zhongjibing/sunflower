package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/08/02.
 */
@Data
public class BookingInfo {


    /**
     * 主键
     */
    private Long id;

    /**
     * 资源id
     */
    @NotNull
    private Long resource;

    /**
     * 资源名称
     */
    @NotBlank
    private String resourceName;

    /**
     * 资源类型
     */
    @NotNull
    private Integer type;

    /**
     * 起始日期
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束日期
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 详细
     */
    @NotBlank
    private String detail;

    /**
     * 附加项
     */
    private String additions;

    /**
     * 注意事项
     */
    private String attentions;

    /**
     * 免费项
     */
    private String gratis;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactMobile;

    /**
     * 状态
     */
    private Integer status;

}

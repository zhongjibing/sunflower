package com.icezhg.sunflower.pojo;

import lombok.Data;

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
    private Long resource;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源类型
     */
    private Integer type;

    /**
     * 起始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 详细
     */
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
     * 状态
     */
    private Integer status;

}

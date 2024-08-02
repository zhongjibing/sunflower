package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/08/03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Booking extends BaseEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 序列号
     */
    private String serialNumber;

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

    /**
     * 渠道
     */
    private Integer channel;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

}

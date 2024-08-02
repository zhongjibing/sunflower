package com.icezhg.sunflower.pojo;

import lombok.Data;

/**
 * Created by zhongjibing on 2023/08/02.
 */
@Data
public class OrderInfo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderId;
    /**
     * 订单日期
     */
    private String date;
    private String startDate;
    private String endDate;

    /**
     * 类型
     *
     * @see com.icezhg.sunflower.enums.ResourceType
     */
    private Integer type;

    /**
     * 子类型
     *
     * @see com.icezhg.sunflower.enums.ResourceSubType
     */
    private Integer subType;

    /**
     * 资源id
     */
    private Long resource;
    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 是否子订单
     */
    private Integer subOrder;
    /**
     * 父级订单号
     */
    private String parentOrderId;


}

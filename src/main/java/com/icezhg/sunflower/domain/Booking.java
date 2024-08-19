package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactMobile;

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

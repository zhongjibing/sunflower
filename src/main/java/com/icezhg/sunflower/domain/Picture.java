package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;


@Data
public class Picture {
    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片MD5
     */
    private String md5;

    /**
     * 图片类型
     */
    private String type;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图片数据
     */
    private byte[] data;

}

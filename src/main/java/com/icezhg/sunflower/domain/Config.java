package com.icezhg.sunflower.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zhongjibing on 2021/01/11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Config extends BaseEntity {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 键名
     */
    private String key;

    /**
     * 键值
     */
    private String value;

    /**
     * 系统内置（Y是 N否）
     */
    private String type;
}

package com.icezhg.sunflower.domain;

import lombok.Data;

/**
 * Created by zhongjibing on 2021/01/11
 */
@Data
public class BinaryData {
    /**
     * 主键
     */
    private Long id;

    /**
     * MD5
     */
    private String md5;

    /**
     * 数据
     */
    private byte[] data;

}

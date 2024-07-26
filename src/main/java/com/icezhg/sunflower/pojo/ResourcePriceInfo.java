package com.icezhg.sunflower.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@Data
public class ResourcePriceInfo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 规则详细
     */
    private String detail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 招待标准
     */
    private List<Standard> standards;


    @Data
    public static class Standard {
        private Long id;
        private String name;
        private String detail;
        private List<Price> prices;
    }

    @Data
    public static class Price {
        /**
         * 日期
         */
        private Date date;

        /**
         * 价格
         */
        private Long price;
    }


}

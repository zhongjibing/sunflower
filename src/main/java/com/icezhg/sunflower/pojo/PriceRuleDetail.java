package com.icezhg.sunflower.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@Data
public class PriceRuleDetail {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 客户标签
     */
    private Integer tagId;

    /**
     * 客户标签
     */
    private String tagName;

    /**
     * 客户标签样式
     */
    private String styleClass;

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源类型
     */
    private Integer type;

    /**
     * 规则详细
     */
    private String detail;

    /**
     * 是否删除
     */
    private Integer deleted;

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
}

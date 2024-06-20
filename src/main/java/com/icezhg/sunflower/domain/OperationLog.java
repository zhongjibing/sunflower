package com.icezhg.sunflower.domain;

import lombok.Data;

import java.util.Date;

/**
 * 操作日志记录
 */
@Data
public class OperationLog {

    /**
     * 主键
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 主机地址
     */
    private String requestIp;

    /**
     * 操作地点
     */
    private String requestLocation;

    /**
     * 请求参数
     */
    private String parameter;

    /**
     * 返回参数
     */
    private String result;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作状态（0正常 1异常）
     */
    private String status;

    /**
     * 操作耗时
     */
    private Long cost;

    /**
     * 操作人员
     */
    private Long userId;

    /**
     * 操作时间
     */
    private Date createTime;

}

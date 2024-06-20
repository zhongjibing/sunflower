package com.icezhg.sunflower.domain;

import java.util.Date;


/**
 * ip地理位置
 *
 * @TableName t_ip_location
 */
public class IpLocation {

    /**
     * ip地址
     */
    private String ip;

    /**
     * ip地理位置
     */
    private String location;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public IpLocation() {
    }

    public IpLocation(String ip, String location) {
        this.ip = ip;
        this.location = location;
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    /**
     * ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * ip地理位置
     */
    public String getLocation() {
        return location;
    }

    /**
     * ip地理位置
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

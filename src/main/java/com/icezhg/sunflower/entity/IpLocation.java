package com.icezhg.sunflower.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * ip地理位置
 *
 * @TableName t_ip_location
 */
@Entity
@Table(name = "t_ip_location")
public class IpLocation implements Serializable {
    @Serial
    private static final long serialVersionUID = 5206140876642538731L;

    /**
     * ip地址
     */
    @Id
    private String ip;

    /**
     * ip地理位置
     */
    private String location;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
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

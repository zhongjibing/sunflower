package com.icezhg.sunflower.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhongjibing on 2022/09/13.
 */
@Data
public class RoleAuth {

    private Integer id;

    private String name;

    private String roleKey;

    private Integer roleSort;

    private Date createTime;

    private String remark;

    private boolean granted;
}

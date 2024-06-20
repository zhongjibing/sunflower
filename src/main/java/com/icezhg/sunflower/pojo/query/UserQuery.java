package com.icezhg.sunflower.pojo.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zhongjibing on 2022/09/04.
 */
@Setter
@Getter
public class UserQuery extends NameQuery {
    private String username;
    private String mobile;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private Integer roleId;
    private Integer nonRoleId;
}

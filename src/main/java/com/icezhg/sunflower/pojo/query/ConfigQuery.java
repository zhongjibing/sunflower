package com.icezhg.sunflower.pojo.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zhongjibing on 2022/09/17.
 */
@Getter
@Setter
public class ConfigQuery extends PageQuery {
    private String name;
    private String key;
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}

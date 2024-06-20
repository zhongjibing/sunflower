package com.icezhg.sunflower.pojo.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zhongjibing on 2022/09/16.
 */
@Getter
@Setter
public class DictQuery extends PageQuery {
    private String dictName;
    private String dictType;
    private String dictLabel;
    private String dictValue;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}

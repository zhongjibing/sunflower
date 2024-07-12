package com.icezhg.sunflower.pojo.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Getter
@Setter
public class InventoryQuery extends PageQuery {
    private String name;
    private Integer type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}

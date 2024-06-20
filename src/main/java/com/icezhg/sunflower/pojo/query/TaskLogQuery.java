package com.icezhg.sunflower.pojo.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Setter
@Getter
public class TaskLogQuery extends PageQuery {

    private Long taskId;
    private String taskName;
    private String taskGroup;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

}

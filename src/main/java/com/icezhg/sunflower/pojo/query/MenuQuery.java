package com.icezhg.sunflower.pojo.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by zhongjibing on 2022/09/17.
 */
@Getter
@Setter
public class MenuQuery extends FuzzyQuery {

    private String name;
    private String status;
    private Long userId;
    private List<String> types;
}

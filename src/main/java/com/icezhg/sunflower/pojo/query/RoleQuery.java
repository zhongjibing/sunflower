package com.icezhg.sunflower.pojo.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhongjibing on 2022/09/04.
 */
@Setter
@Getter
public class RoleQuery extends PageQuery {
    private Integer id;
    private String name;
    private String roleKey;
    private String status;
}

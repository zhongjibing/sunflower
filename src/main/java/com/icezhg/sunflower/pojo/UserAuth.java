package com.icezhg.sunflower.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created by zhongjibing on 2022/09/13.
 */
@Data
public class UserAuth {

    private Long id;

    private String username;

    private String nickname;

    List<RoleAuth> roleAuths;
}

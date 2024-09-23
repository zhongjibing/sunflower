package com.icezhg.sunflower.pojo;

import lombok.Data;

/**
 * Created by zhongjibing on 2023/08/23.
 */
@Data
public class OpenidUser {
    /**
     * id
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 备注
     */
    private String remark;
}

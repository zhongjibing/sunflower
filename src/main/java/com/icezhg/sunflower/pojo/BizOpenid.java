package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Created by zhongjibing on 2023/08/11.
 */
@Data
public class BizOpenid {
    private String avatar;
    @NotBlank
    private String nickname;
}

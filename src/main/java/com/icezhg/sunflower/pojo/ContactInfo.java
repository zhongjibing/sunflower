package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Created by zhongjibing on 2023/08/02.
 */
@Data
public class ContactInfo {

    @NotBlank
    private String name;
    @NotBlank
    private String mobile;
}

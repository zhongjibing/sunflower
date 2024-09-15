package com.icezhg.sunflower.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created by zhongjibing on 2023/08/16.
 */
@Data
public class Option {

    private OptionTypeInfo optionType;
    private List<OptionDataInfo> optionDataList;
}

package com.icezhg.sunflower.pojo;

import lombok.Data;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Data
public class PriceDetail {

    private long price;
    private long weekend;
    private long holiday;
    private boolean weekendEnabled;
    private boolean holidayEnabled;
}

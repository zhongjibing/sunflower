package com.icezhg.sunflower.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created by zhongjibing on 2023/08/03.
 */
@Data
public class BookingObject {

    private List<BookingInfo> bookingInfos;

    private List<ContactInfo> contactInfo;
}

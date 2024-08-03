package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * Created by zhongjibing on 2023/08/03.
 */
@Data
public class BookingObject {

    @NotEmpty
    private List<BookingInfo> bookingInfos;

    @NotNull
    private ContactInfo contactInfo;
}

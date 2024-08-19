package com.icezhg.sunflower.service;

import com.icezhg.sunflower.pojo.BookingInfo;
import com.icezhg.sunflower.pojo.BookingObject;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

/**
 * Created by zhongjibing on 2023/08/02.
 */
public interface BookingManageService {

    List<BookingInfo> create(BookingObject bookingObject);

    BookingInfo modify(BookingInfo bookingInfo);

    List<BookingInfo> find(Query query);

    BookingInfo findById(Long detailId);

    int confirm(BookingInfo bookingInfo);

    int cancel(BookingInfo bookingInfo);

}

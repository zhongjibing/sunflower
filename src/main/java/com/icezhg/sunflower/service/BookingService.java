package com.icezhg.sunflower.service;

import com.icezhg.sunflower.enums.BookingStatus;
import com.icezhg.sunflower.pojo.BookingInfo;
import com.icezhg.sunflower.pojo.BookingObject;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

/**
 * Created by zhongjibing on 2023/08/02.
 */
public interface BookingService {

    List<BookingInfo> create(BookingObject bookingObject);

    BookingInfo modify(BookingInfo bookingInfo);

    List<BookingInfo> find(Query query);

    List<BookingInfo> findAll(Query query);

    BookingInfo findById(Long detailId);

    int confirm(Long id);

    int cancel(Long id);

    int hide(Long id);

    int delete(Long id);

    void assertModifyStatus(Long detailId, BookingStatus status);
}

package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.BookingDao;
import com.icezhg.sunflower.pojo.BookingInfo;
import com.icezhg.sunflower.pojo.BookingObject;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongjibing on 2023/08/02.
 */
@Service
public class BookingServiceImpl implements BookingService {


    private BookingDao bookingDao;

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public List<BookingInfo> create(BookingObject bookingObject) {
        return null;
    }

    @Override
    public BookingInfo modify(BookingInfo bookingInfo) {
        return null;
    }

    @Override
    public List<BookingInfo> find(Query query) {
        return null;
    }

    @Override
    public BookingInfo findById(Long id) {
        return null;
    }

    @Override
    public int confirm(BookingInfo bookingInfo) {
        return 0;
    }

    @Override
    public int cancel(BookingInfo bookingInfo) {
        return 0;
    }
}

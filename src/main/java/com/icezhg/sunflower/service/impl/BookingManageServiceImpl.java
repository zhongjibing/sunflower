package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.exception.InvalidAccessException;
import com.icezhg.commons.util.IdGenerator;
import com.icezhg.sunflower.dao.BookingDao;
import com.icezhg.sunflower.dao.BookingDetailDao;
import com.icezhg.sunflower.domain.Booking;
import com.icezhg.sunflower.domain.BookingDetail;
import com.icezhg.sunflower.enums.BookingStatus;
import com.icezhg.sunflower.enums.Channel;
import com.icezhg.sunflower.pojo.BookingInfo;
import com.icezhg.sunflower.pojo.BookingObject;
import com.icezhg.sunflower.pojo.ContactInfo;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.BookingManageService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by zhongjibing on 2023/08/02.
 */
@Service
public class BookingManageServiceImpl implements BookingManageService {


    private BookingDao bookingDao;

    private BookingDetailDao bookingDetailDao;

    public BookingManageServiceImpl(BookingDao bookingDao, BookingDetailDao bookingDetailDao) {
        this.bookingDao = bookingDao;
        this.bookingDetailDao = bookingDetailDao;
    }

    @Override
    @Transactional
    public List<BookingInfo> create(BookingObject bookingObject) {
        Booking booking = buildBooking(bookingObject);
        bookingDao.insert(booking);

        List<BookingDetail> details = buildBookingDetails(booking, bookingObject.getBookingInfos());
        if (!details.isEmpty()) {
            bookingDetailDao.batchInsert(details);
        }
        return details.stream().map(this::buildBookingInfo).collect(Collectors.toList());
    }

    private List<BookingDetail> buildBookingDetails(Booking booking, List<BookingInfo> bookingInfos) {
        List<BookingDetail> details = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(bookingInfos)) {
            bookingInfos.forEach(info -> details.add(buildBookingDetail(booking, info)));
        }
        return details;
    }

    private BookingDetail buildBookingDetail(Booking booking, BookingInfo bookingInfo) {
        BookingDetail detail = new BookingDetail();
        detail.setSerialNumber(booking.getSerialNumber());
        detail.setResource(bookingInfo.getResource());
        detail.setResourceName(bookingInfo.getResourceName());
        detail.setType(bookingInfo.getType());
        detail.setStartDate(bookingInfo.getStartDate());
        detail.setEndDate(bookingInfo.getEndDate());
        detail.setDetail(StringUtils.defaultString(bookingInfo.getDetail()));
        detail.setAdditions(StringUtils.defaultString(bookingInfo.getAdditions()));
        detail.setAttentions(StringUtils.defaultString(bookingInfo.getAttentions()));
        detail.setGratis(StringUtils.defaultString(bookingInfo.getGratis()));
        detail.setContactName(StringUtils.defaultString(booking.getContactName()));
        detail.setContactMobile(StringUtils.defaultString(booking.getContactMobile()));
        detail.setStatus(booking.getStatus());
        detail.setChannel(booking.getChannel());
        detail.setCreateBy(StringUtils.defaultString(booking.getCreateBy()));
        detail.setCreateTime(booking.getCreateTime());
        detail.setUpdateBy(StringUtils.defaultString(booking.getUpdateBy()));
        detail.setUpdateTime(booking.getUpdateTime());
        detail.setRemark("");
        return detail;
    }

    private BookingInfo buildBookingInfo(BookingDetail bookingDetail) {
        BookingInfo info = new BookingInfo();
        info.setDetailId(bookingDetail.getId());
        info.setResource(bookingDetail.getResource());
        info.setResourceName(bookingDetail.getResourceName());
        info.setType(bookingDetail.getType());
        info.setStartDate(bookingDetail.getStartDate());
        info.setEndDate(bookingDetail.getEndDate());
        info.setDetail(bookingDetail.getDetail());
        info.setAdditions(bookingDetail.getAdditions());
        info.setAttentions(bookingDetail.getAttentions());
        info.setGratis(bookingDetail.getGratis());
        info.setContactName(bookingDetail.getContactName());
        info.setContactMobile(bookingDetail.getContactMobile());
        info.setStatus(bookingDetail.getStatus());
        return info;
    }


    private Booking buildBooking(BookingObject bookingObject) {
        Booking booking = new Booking();
        booking.setSerialNumber(IdGenerator.nextId());
        ContactInfo contactInfo = bookingObject.getContactInfo();
        if (contactInfo != null) {
            booking.setContactMobile(contactInfo.getMobile());
            booking.setContactName(contactInfo.getName());
        }
        booking.setStatus(BookingStatus.CHECKING.getStatus());
        booking.setChannel(Channel.WX_MINI.getChannel());

        String username = SecurityUtil.currentUserName();
        booking.setUserId(SecurityUtil.currentUserId());
        booking.setUsername(username);
        booking.setCreateBy(username);
        booking.setCreateTime(new Date());
        booking.setUpdateBy(username);
        booking.setUpdateTime(new Date());
        return booking;
    }

    @Override
    public BookingInfo modify(BookingInfo bookingInfo) {
        return null;
    }

    @Override
    public List<BookingInfo> find(Query query) {
        Map<String, Object> params = query.toMap();
        params.put("userId", SecurityUtil.currentUserId());
        params.put("username", SecurityUtil.currentUserName());
        return bookingDetailDao.find(params).stream().map(this::buildBookingInfo).collect(Collectors.toList());
    }

    @Override
    public BookingInfo findById(Long detailId) {
        BookingDetail detail = bookingDetailDao.findById(detailId);
        return detail != null ? buildBookingInfo(detail) : null;
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

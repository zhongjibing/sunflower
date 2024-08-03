package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.BookingDetail;
import com.icezhg.sunflower.pojo.BookingInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/08/03
 */
@Repository
public interface BookingDetailDao {

    int insert(BookingDetail record);

    BookingDetail findById(Long id);

    int update(BookingDetail record);

    int batchInsert(List<BookingDetail> records);

    List<BookingDetail> find(Map<String, Object> params);
}

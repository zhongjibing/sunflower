package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.BookingDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhongjibing on 2023/08/03
 */
@Repository
public interface BookingDetailDao {

    int insert(BookingDetail record);

    BookingDetail findById(Long id);

    int update(BookingDetail record);

    int batchInsert(List<BookingDetail> records);
}

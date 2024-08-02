package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.Booking;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/08/03
 */
@Repository
public interface BookingDao {

    int insert(Booking record);

    int update(Booking record);

    Booking findById(Long id);

    List<Booking> find(Map<String, Object> param);

}

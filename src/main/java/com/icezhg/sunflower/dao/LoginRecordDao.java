package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.LoginRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2022/012/15.
 */
@Repository
public interface LoginRecordDao {

    int save(LoginRecord loginRecord);

    int count(Map<String, Object> query);

    List<LoginRecord> find(Map<String, Object> query);

}

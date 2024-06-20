package com.icezhg.sunflower.repository;

import com.icezhg.sunflower.entity.LoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhongjibing on 2022/12/12.
 */
@Repository
public interface LoginRecordRepository extends JpaRepository<LoginRecord, Long> {
}

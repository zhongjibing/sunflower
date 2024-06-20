package com.icezhg.sunflower.repository;

import com.icezhg.sunflower.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhongjibing on 2023/06/20.
 */
@Repository
public interface OperationLogRepository extends JpaRepository<OperationLog, Long> {


}

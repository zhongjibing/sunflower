package com.icezhg.sunflower.repository;

import com.icezhg.sunflower.entity.IpLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhongjibing on 2022/12/23.
 */
@Repository
public interface IpLocationRepository extends JpaRepository<IpLocation, String> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into t_ip_location(ip, location, create_time, update_time) value (" +
            ":#{#entity.ip}, :#{#entity.location}, :#{#entity.createTime}, :#{#entity.updateTime}" +
            ") on duplicate key update location = :#{#entity.location}, update_time = :#{#entity.updateTime}")
    int createOrUpdate(IpLocation entity);

    IpLocation findByIp(String ip);

}

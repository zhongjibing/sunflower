package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.IpLocation;
import org.springframework.stereotype.Repository;

/**
 * Created by zhongjibing on 2022/12/24.
 */
@Repository
public interface IpLocationDao {

    int save(IpLocation ipLocation);

    IpLocation findByIp(String ip);
}

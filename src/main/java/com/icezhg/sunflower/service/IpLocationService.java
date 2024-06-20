package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.IpLocation;

/**
 * Created by zhongjibing on 2023/06/20.
 */
public interface IpLocationService {

    void save(IpLocation ipLocation);

    IpLocation findByIp(String ip);

    String search(String ip);
}

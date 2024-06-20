package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.IpLocationDao;
import com.icezhg.sunflower.domain.IpLocation;
import com.icezhg.sunflower.service.IpLocationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by zhongjibing on 2022/12/24.
 */
@Service
public class IpLocationServiceImpl implements IpLocationService {

    private final IpLocationDao ipLocationDao;

    public IpLocationServiceImpl(IpLocationDao ipLocationDao) {
        this.ipLocationDao = ipLocationDao;
    }

    @Override
    public void save(IpLocation ipLocation) {
        ipLocationDao.save(ipLocation);
    }

    @Override
    public IpLocation findByIp(String ip) {
        return ipLocationDao.findByIp(ip);
    }

    @Override
    public String search(String ip) {
        if (StringUtils.isNotBlank(ip)) {
            IpLocation ipLocation = ipLocationDao.findByIp(ip);
            if (ipLocation != null) {
                return StringUtils.defaultString(ipLocation.getLocation());
            }
        }
        return StringUtils.EMPTY;
    }
}

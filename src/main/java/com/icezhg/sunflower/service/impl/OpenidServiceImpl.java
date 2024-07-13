package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.OpenidDao;
import com.icezhg.sunflower.domain.Openid;
import com.icezhg.sunflower.service.OpenidService;
import org.springframework.stereotype.Service;

/**
 * Created by zhongjibing on 2023/07/13.
 */
@Service
public class OpenidServiceImpl implements OpenidService {

    private final OpenidDao openidDao;

    public OpenidServiceImpl(OpenidDao openidDao) {
        this.openidDao = openidDao;
    }

    @Override
    public Openid findByOpenid(String openid) {
        return this.openidDao.findByOpenid(openid);
    }

    @Override
    public void save(Openid openid) {
        this.openidDao.insert(openid);
    }
}

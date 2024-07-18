package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.OpenidDao;
import com.icezhg.sunflower.domain.Openid;
import com.icezhg.sunflower.pojo.ChangeStatus;
import com.icezhg.sunflower.pojo.OpenidInfo;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.OpenidService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public Openid findById(Long id) {
        return this.openidDao.findById(id);
    }

    @Override
    public int count(Query query) {
        return this.openidDao.count(query.toMap());
    }

    @Override
    public List<Openid> find(Query query) {
        return this.openidDao.find(query.toMap());
    }

    @Override
    public int changeStatus(ChangeStatus change) {
        Openid openid = new Openid();
        openid.setId(change.getId());
        openid.setStatus(change.getStatus());
        openid.setUpdateTime(new Date());
        return this.openidDao.update(openid);
    }

    @Override
    public OpenidInfo update(OpenidInfo info) {
        this.openidDao.update(buildOpenid(info));
        return buildOpenidInfo(findById(info.getId()));
    }

    private Openid buildOpenid(OpenidInfo info) {
        Openid openid = new Openid();
        openid.setId(info.getId());
        openid.setNickname(info.getNickname());
        openid.setMobile(info.getMobile());
        openid.setRole(info.getRole());
        openid.setStatus(info.getStatus());
        openid.setRemark(info.getRemark());
        openid.setUpdateTime(new Date());
        return openid;
    }

    private OpenidInfo buildOpenidInfo(Openid openid) {
        OpenidInfo info = new OpenidInfo();
        if (openid != null) {
            info.setId(openid.getId());
            info.setNickname(openid.getNickname());
            info.setMobile(openid.getMobile());
            info.setRole(openid.getRole());
            info.setStatus(openid.getStatus());
            info.setCreateTime(openid.getCreateTime());
            info.setUpdateTime(openid.getUpdateTime());
            info.setRemark(openid.getRemark());
        }
        return info;
    }
}

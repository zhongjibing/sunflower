package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.ResourceDao;
import com.icezhg.sunflower.domain.Resource;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceDao resourceDao;

    public ResourceServiceImpl(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    @Override
    public void insert(Resource resource) {

    }

    @Override
    public void update(Resource resource) {

    }

    @Override
    public Resource findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public int count(Query query) {
        return 0;
    }

    @Override
    public List<Resource> find(Query query) {
        return null;
    }

    private void
}

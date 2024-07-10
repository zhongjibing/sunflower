package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.CustomerTag;
import com.icezhg.sunflower.pojo.CustomerTagInfo;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/10.
 */
public interface CustomerTagService {

    CustomerTag insert(CustomerTagInfo info);

    CustomerTag update(CustomerTagInfo info);

    CustomerTag findById(Integer id);

    void deleteByIds(List<Integer> tagIds);

    int count(Query query);

    List<CustomerTag> find(Query query);

    List<CustomerTag> listAll();
}

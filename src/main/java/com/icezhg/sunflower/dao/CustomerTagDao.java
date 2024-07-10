package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.CustomerTag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/07/10.
 */
@Repository
public interface CustomerTagDao {

    int delete(Integer id);

    int deleteByIds(List<Integer> tagIds);

    int insert(CustomerTag record);

    CustomerTag findById(Integer id);

    int update(CustomerTag record);

    int count(Map<String, Object> params);

    List<CustomerTag> find(Map<String, Object> params);

    List<CustomerTag> findAll();

}

package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.DictType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2022/09/08.
 */
@Repository
public interface DictTypeDao {

    int deleteByIds(List<Integer> ids);

    int insert(DictType record);

    DictType findById(Integer id);

    int update(DictType record);

    int count(Map<String, Object> toMap);

    List<DictType> find(Map<String, Object> toMap);
}

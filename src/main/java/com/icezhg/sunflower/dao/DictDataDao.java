package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.DictData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2022/09/08.
 */
@Repository
public interface DictDataDao {

    int deleteByIds(List<Integer> ids);

    int deleteByTypeIds(List<Integer> typeIds);

    int insert(DictData record);

    int update(DictData record);

    List<DictData> findByType(String dictType);

    DictData findById(Integer id);

    int count(Map<String, Object> toMap);

    List<DictData> find(Map<String, Object> toMap);
}

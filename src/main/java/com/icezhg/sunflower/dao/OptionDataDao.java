package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.OptionData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/08/15.
 */
@Repository
public interface OptionDataDao {

    int deleteByIds(List<Integer> ids);

    int deleteByTypeIds(List<Integer> typeIds);

    int insert(OptionData record);

    int update(OptionData record);

    List<OptionData> findByType(String dictType);

    OptionData findById(Integer id);

    int count(Map<String, Object> toMap);

    List<OptionData> find(Map<String, Object> toMap);

}

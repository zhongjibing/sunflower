package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.OptionType;
import com.icezhg.sunflower.pojo.Option;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/08/15.
 */
@Repository
public interface OptionTypeDao {

    int deleteByIds(List<Integer> ids);

    int insert(OptionType record);

    OptionType findById(Integer id);

    int update(OptionType record);

    int count(Map<String, Object> toMap);

    List<OptionType> find(Map<String, Object> toMap);

    List<Option> listAllOptions();

}

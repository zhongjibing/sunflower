package com.icezhg.sunflower.service;


import com.icezhg.sunflower.domain.OptionData;
import com.icezhg.sunflower.domain.OptionType;
import com.icezhg.sunflower.pojo.OptionTypeInfo;
import com.icezhg.sunflower.pojo.query.OptionQuery;

import java.util.List;

/**
 * 字典 业务层处理
 */
public interface OptionTypeService {

    List<OptionData> findOptionDataByType(String dictType);

    int count(OptionQuery query);

    List<OptionType> find(OptionQuery query);

    OptionTypeInfo findById(Integer id);

    boolean checkUnique(OptionTypeInfo typeInfo);

    OptionTypeInfo save(OptionTypeInfo typeInfo);

    OptionTypeInfo update(OptionTypeInfo typeInfo);

    int deleteByIds(List<Integer> ids);

    List<OptionTypeInfo> listOptions();

}

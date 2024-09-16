package com.icezhg.sunflower.service;


import com.icezhg.sunflower.domain.OptionData;
import com.icezhg.sunflower.pojo.Option;
import com.icezhg.sunflower.pojo.OptionDataInfo;
import com.icezhg.sunflower.pojo.query.OptionQuery;

import java.util.List;

/**
 * 字典 业务层处理
 */
public interface OptionDataService {

    OptionDataInfo save(OptionDataInfo dataInfo);

    OptionDataInfo update(OptionDataInfo dataInfo);

    OptionDataInfo findById(Integer id);

    int count(OptionQuery query);

    List<OptionData> find(OptionQuery query);

    int deleteByIds(List<Integer> ids);

    List<Option> collectAll();
}

package com.icezhg.sunflower.service;


import com.icezhg.sunflower.domain.DictData;
import com.icezhg.sunflower.pojo.DictDataInfo;
import com.icezhg.sunflower.pojo.query.DictQuery;

import java.util.List;

/**
 * 字典 业务层处理
 */
public interface DictDataService {

    DictDataInfo save(DictDataInfo dataInfo);

    DictDataInfo update(DictDataInfo dataInfo);

    DictDataInfo findById(Integer id);

    int count(DictQuery query);

    List<DictData> find(DictQuery query);

    int deleteByIds(List<Integer> ids);


}

package com.icezhg.sunflower.service;


import com.icezhg.sunflower.domain.DictData;
import com.icezhg.sunflower.domain.DictType;
import com.icezhg.sunflower.pojo.DictTypeInfo;
import com.icezhg.sunflower.pojo.query.DictQuery;

import java.util.List;

/**
 * 字典 业务层处理
 */
public interface DictTypeService {


    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<DictData> findDictDataByType(String dictType);

    int count(DictQuery query);

    List<DictType> find(DictQuery query);

    DictTypeInfo findById(Integer id);

    boolean checkUnique(DictTypeInfo typeInfo);

    DictTypeInfo save(DictTypeInfo typeInfo);

    DictTypeInfo update(DictTypeInfo typeInfo);

    int deleteByIds(List<Integer> ids);

    List<DictTypeInfo> listOptions();
}

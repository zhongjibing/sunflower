package com.icezhg.sunflower.service.impl;


import com.icezhg.sunflower.dao.DictDataDao;
import com.icezhg.sunflower.dao.DictTypeDao;
import com.icezhg.sunflower.domain.DictData;
import com.icezhg.sunflower.domain.DictType;
import com.icezhg.sunflower.pojo.DictTypeInfo;
import com.icezhg.sunflower.pojo.query.DictQuery;
import com.icezhg.sunflower.service.DictTypeService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 字典 业务层处理
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {

    private final DictTypeDao dictTypeDao;

    private final DictDataDao dictDataDao;

    public DictTypeServiceImpl(DictTypeDao dictTypeDao, DictDataDao dictDataDao) {
        this.dictTypeDao = dictTypeDao;
        this.dictDataDao = dictDataDao;
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<DictData> findDictDataByType(String dictType) {
        return dictDataDao.findByType(dictType);
    }

    @Override
    public int count(DictQuery query) {
        return dictTypeDao.count(query.toMap());
    }

    @Override
    public List<DictType> find(DictQuery query) {
        return dictTypeDao.find(query.toMap());
    }

    @Override
    public DictTypeInfo findById(Integer id) {
        return buildDictTypeInfo(dictTypeDao.findById(id));
    }

    @Override
    public boolean checkUnique(DictTypeInfo typeInfo) {
        DictQuery query = new DictQuery();
        query.setDictType(typeInfo.getDictType());
        List<DictType> dictTypes = find(query);
        return dictTypes.isEmpty() || Objects.equals(typeInfo.getId(), dictTypes.get(0).getId());
    }

    @Override
    public DictTypeInfo save(DictTypeInfo typeInfo) {
        DictType dictType = buildDictType(typeInfo);
        dictType.setCreateBy(SecurityUtil.currentUserName());
        dictType.setCreateTime(new Date());
        dictType.setUpdateBy(SecurityUtil.currentUserName());
        dictType.setUpdateTime(new Date());
        dictTypeDao.insert(dictType);
        typeInfo.setId(dictType.getId());
        return typeInfo;
    }

    @Override
    public DictTypeInfo update(DictTypeInfo typeInfo) {
        DictType dictType = buildDictType(typeInfo);
        dictType.setUpdateBy(SecurityUtil.currentUserName());
        dictType.setUpdateTime(new Date());
        dictTypeDao.update(dictType);
        return findById(typeInfo.getId());
    }

    private DictType buildDictType(DictTypeInfo typeInfo) {
        DictType dictType = new DictType();
        dictType.setId(typeInfo.getId());
        dictType.setDictName(typeInfo.getDictName());
        dictType.setDictType(typeInfo.getDictType());
        dictType.setStatus(typeInfo.getStatus());
        dictType.setRemark(typeInfo.getRemark());
        return dictType;
    }

    private DictTypeInfo buildDictTypeInfo(DictType dictType) {
        DictTypeInfo typeInfo = new DictTypeInfo();
        if (dictType != null) {
            typeInfo.setId(dictType.getId());
            typeInfo.setDictName(dictType.getDictName());
            typeInfo.setDictType(dictType.getDictType());
            typeInfo.setStatus(dictType.getStatus());
            typeInfo.setRemark(dictType.getRemark());
        }
        return typeInfo;
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        dictDataDao.deleteByTypeIds(ids);
        return dictTypeDao.deleteByIds(ids);
    }

    @Override
    public List<DictTypeInfo> listOptions() {
        DictQuery query = new DictQuery();
        query.setPageSize(Integer.MAX_VALUE);
        return find(query).stream().map(this::buildDictTypeInfo).toList();
    }
}

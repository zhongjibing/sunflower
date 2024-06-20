package com.icezhg.sunflower.service.impl;


import com.icezhg.sunflower.dao.DictDataDao;
import com.icezhg.sunflower.domain.DictData;
import com.icezhg.sunflower.pojo.DictDataInfo;
import com.icezhg.sunflower.pojo.query.DictQuery;
import com.icezhg.sunflower.service.DictDataService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 字典 业务层处理
 */
@Service
public class DictDataServiceImpl implements DictDataService {

    private final DictDataDao dictDataDao;

    public DictDataServiceImpl(DictDataDao dictDataDao) {
        this.dictDataDao = dictDataDao;
    }

    @Override
    public DictDataInfo save(DictDataInfo dataInfo) {
        DictData dictData = buildDictData(dataInfo);
        dictData.setCreateBy(SecurityUtil.currentUserName());
        dictData.setCreateTime(new Date());
        dictData.setUpdateBy(SecurityUtil.currentUserName());
        dictData.setUpdateTime(new Date());
        dictDataDao.insert(dictData);
        dataInfo.setId(dictData.getId());
        return dataInfo;
    }

    @Override
    public DictDataInfo update(DictDataInfo dataInfo) {
        DictData dictData = buildDictData(dataInfo);
        dictData.setUpdateBy(SecurityUtil.currentUserName());
        dictData.setUpdateTime(new Date());
        dictDataDao.update(dictData);
        return findById(dataInfo.getId());
    }

    @Override
    public DictDataInfo findById(Integer id) {
        return buildDictDataInfo(dictDataDao.findById(id));
    }

    @Override
    public int count(DictQuery query) {
        return dictDataDao.count(query.toMap());
    }

    @Override
    public List<DictData> find(DictQuery query) {
        return dictDataDao.find(query.toMap());
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        return dictDataDao.deleteByIds(ids);
    }

    private DictData buildDictData(DictDataInfo dataInfo) {
        DictData dictData = new DictData();
        dictData.setId(dataInfo.getId());
        dictData.setDictSort(dataInfo.getDictSort());
        dictData.setDictLabel(dataInfo.getDictLabel());
        dictData.setDictValue(dataInfo.getDictValue());
        dictData.setDictValueType(dataInfo.getDictValueType());
        dictData.setDictType(dataInfo.getDictType());
        dictData.setCssClass(dataInfo.getCssClass());
        dictData.setListClass(dataInfo.getListClass());
        dictData.setIsDefault(dataInfo.getIsDefault());
        dictData.setStatus(dataInfo.getStatus());
        dictData.setRemark(dataInfo.getRemark());
        return dictData;
    }

    private DictDataInfo buildDictDataInfo(DictData dictData) {
        DictDataInfo dataInfo = new DictDataInfo();
        if (dictData != null) {
            dataInfo.setId(dictData.getId());
            dataInfo.setDictType(dictData.getDictType());
            dataInfo.setDictSort(dictData.getDictSort());
            dataInfo.setDictLabel(dictData.getDictLabel());
            dataInfo.setDictValue(dictData.getDictValue());
            dataInfo.setDictValueType(dictData.getDictValueType());
            dataInfo.setCssClass(dictData.getCssClass());
            dataInfo.setListClass(dictData.getListClass());
            dataInfo.setIsDefault(dictData.getIsDefault());
            dataInfo.setStatus(dictData.getStatus());
            dataInfo.setRemark(dictData.getRemark());
        }
        return dataInfo;
    }
}

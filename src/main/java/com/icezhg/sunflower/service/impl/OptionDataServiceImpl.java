package com.icezhg.sunflower.service.impl;


import com.icezhg.sunflower.dao.OptionDataDao;
import com.icezhg.sunflower.domain.OptionData;
import com.icezhg.sunflower.pojo.OptionDataInfo;
import com.icezhg.sunflower.pojo.query.OptionQuery;
import com.icezhg.sunflower.service.OptionDataService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 字典 业务层处理
 */
@Service
public class OptionDataServiceImpl implements OptionDataService {

    private final OptionDataDao optionDataDao;

    public OptionDataServiceImpl(OptionDataDao optionDataDao) {
        this.optionDataDao = optionDataDao;
    }

    @Override
    public OptionDataInfo save(OptionDataInfo dataInfo) {
        OptionData optionData = buildOptionData(dataInfo);
        optionData.setCreateBy(SecurityUtil.currentUserName());
        optionData.setCreateTime(new Date());
        optionData.setUpdateBy(SecurityUtil.currentUserName());
        optionData.setUpdateTime(new Date());
        optionDataDao.insert(optionData);
        dataInfo.setId(optionData.getId());
        return dataInfo;
    }

    @Override
    public OptionDataInfo update(OptionDataInfo dataInfo) {
        OptionData optionData = buildOptionData(dataInfo);
        optionData.setUpdateBy(SecurityUtil.currentUserName());
        optionData.setUpdateTime(new Date());
        optionDataDao.update(optionData);
        return findById(dataInfo.getId());
    }

    @Override
    public OptionDataInfo findById(Integer id) {
        return buildOptionDataInfo(optionDataDao.findById(id));
    }

    @Override
    public int count(OptionQuery query) {
        return optionDataDao.count(query.toMap());
    }

    @Override
    public List<OptionData> find(OptionQuery query) {
        return optionDataDao.find(query.toMap());
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        return optionDataDao.deleteByIds(ids);
    }

    private OptionData buildOptionData(OptionDataInfo dataInfo) {
        OptionData optionData = new OptionData();
        optionData.setId(dataInfo.getId());
        optionData.setOptionSort(dataInfo.getOptionSort());
        optionData.setOptionLabel(dataInfo.getOptionLabel());
        optionData.setOptionValue(dataInfo.getOptionValue());
        optionData.setOptionValueType(dataInfo.getOptionValueType());
        optionData.setOptionType(dataInfo.getOptionType());
        optionData.setCssClass(dataInfo.getCssClass());
        optionData.setListClass(dataInfo.getListClass());
        optionData.setIsDefault(dataInfo.getIsDefault());
        optionData.setStatus(dataInfo.getStatus());
        optionData.setRemark(dataInfo.getRemark());
        return optionData;
    }

    private OptionDataInfo buildOptionDataInfo(OptionData optionData) {
        OptionDataInfo dataInfo = new OptionDataInfo();
        if (optionData != null) {
            dataInfo.setId(optionData.getId());
            dataInfo.setOptionType(optionData.getOptionType());
            dataInfo.setOptionSort(optionData.getOptionSort());
            dataInfo.setOptionLabel(optionData.getOptionLabel());
            dataInfo.setOptionValue(optionData.getOptionValue());
            dataInfo.setOptionValueType(optionData.getOptionValueType());
            dataInfo.setCssClass(optionData.getCssClass());
            dataInfo.setListClass(optionData.getListClass());
            dataInfo.setIsDefault(optionData.getIsDefault());
            dataInfo.setStatus(optionData.getStatus());
            dataInfo.setRemark(optionData.getRemark());
        }
        return dataInfo;
    }
}

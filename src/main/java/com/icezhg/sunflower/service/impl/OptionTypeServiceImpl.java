package com.icezhg.sunflower.service.impl;


import com.icezhg.sunflower.dao.OptionDataDao;
import com.icezhg.sunflower.dao.OptionTypeDao;
import com.icezhg.sunflower.domain.OptionData;
import com.icezhg.sunflower.domain.OptionType;
import com.icezhg.sunflower.pojo.OptionTypeInfo;
import com.icezhg.sunflower.pojo.query.OptionQuery;
import com.icezhg.sunflower.service.OptionTypeService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 字典 业务层处理
 */
@Service
public class OptionTypeServiceImpl implements OptionTypeService {

    private final OptionTypeDao optionTypeDao;

    private final OptionDataDao optionDataDao;

    public OptionTypeServiceImpl(OptionTypeDao optionTypeDao, OptionDataDao optionDataDao) {
        this.optionTypeDao = optionTypeDao;
        this.optionDataDao = optionDataDao;
    }

    @Override
    public List<OptionData> findOptionDataByType(String optionType) {
        return optionDataDao.findByType(optionType);
    }

    @Override
    public int count(OptionQuery query) {
        return optionTypeDao.count(query.toMap());
    }

    @Override
    public List<OptionType> find(OptionQuery query) {
        return optionTypeDao.find(query.toMap());
    }

    @Override
    public OptionTypeInfo findById(Integer id) {
        return buildOptionTypeInfo(optionTypeDao.findById(id));
    }

    @Override
    public boolean checkUnique(OptionTypeInfo typeInfo) {
        OptionQuery query = new OptionQuery();
        query.setOptionType(typeInfo.getOptionType());
        List<OptionType> optionTypes = find(query);
        return optionTypes.isEmpty() || Objects.equals(typeInfo.getId(), optionTypes.get(0).getId());
    }

    @Override
    public OptionTypeInfo save(OptionTypeInfo typeInfo) {
        OptionType optionType = buildOptionType(typeInfo);
        optionType.setCreateBy(SecurityUtil.currentUserName());
        optionType.setCreateTime(new Date());
        optionType.setUpdateBy(SecurityUtil.currentUserName());
        optionType.setUpdateTime(new Date());
        optionTypeDao.insert(optionType);
        typeInfo.setId(optionType.getId());
        return typeInfo;
    }

    @Override
    @Transactional
    public OptionTypeInfo update(OptionTypeInfo typeInfo) {
        if (StringUtils.isNotBlank(typeInfo.getOptionType())) {
            OptionTypeInfo existing = findById(typeInfo.getId());
            if (existing != null && !typeInfo.getOptionType().equals(existing.getOptionType())) {
                optionDataDao.updateOptionType(typeInfo.getOptionType(), existing.getOptionType());
            }
        }

        OptionType optionType = buildOptionType(typeInfo);
        optionType.setUpdateBy(SecurityUtil.currentUserName());
        optionType.setUpdateTime(new Date());
        optionTypeDao.update(optionType);
        return findById(typeInfo.getId());
    }

    private OptionType buildOptionType(OptionTypeInfo typeInfo) {
        OptionType optionType = new OptionType();
        optionType.setId(typeInfo.getId());
        optionType.setOptionName(typeInfo.getOptionName());
        optionType.setOptionType(typeInfo.getOptionType());
        optionType.setStatus(typeInfo.getStatus());
        optionType.setRemark(typeInfo.getRemark());
        return optionType;
    }

    private OptionTypeInfo buildOptionTypeInfo(OptionType optionType) {
        OptionTypeInfo typeInfo = new OptionTypeInfo();
        if (optionType != null) {
            typeInfo.setId(optionType.getId());
            typeInfo.setOptionName(optionType.getOptionName());
            typeInfo.setOptionType(optionType.getOptionType());
            typeInfo.setStatus(optionType.getStatus());
            typeInfo.setRemark(optionType.getRemark());
        }
        return typeInfo;
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        optionDataDao.deleteByTypeIds(ids);
        return optionTypeDao.deleteByIds(ids);
    }

    @Override
    public List<OptionTypeInfo> listOptions() {
        OptionQuery query = new OptionQuery();
        query.setPageSize(Integer.MAX_VALUE);
        return find(query).stream().map(this::buildOptionTypeInfo).toList();
    }

}

package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.exception.InvalidDataStateException;
import com.icezhg.sunflower.dao.CustomerTagDao;
import com.icezhg.sunflower.domain.CustomerTag;
import com.icezhg.sunflower.pojo.CustomerTagInfo;
import com.icezhg.sunflower.pojo.query.DeleteQuery;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.CustomerTagService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongjibing on 2023/07/10.
 */
@Service
public class CustomerTagServiceImpl implements CustomerTagService {

    private final CustomerTagDao customerTagDao;

    public CustomerTagServiceImpl(CustomerTagDao customerTagDao) {
        this.customerTagDao = customerTagDao;
    }

    @Override
    public CustomerTag insert(CustomerTagInfo info) {
        CustomerTag customerTag = buildTag(info);
        customerTagDao.insert(customerTag);
        return customerTag;
    }

    @Override
    public CustomerTag update(CustomerTagInfo info) {
        CustomerTag customerTag = buildTag(info);
        customerTagDao.update(customerTag);
        return findById(info.getId());
    }

    @Override
    public CustomerTag findById(Integer id) {
        return customerTagDao.findById(id);
    }

    @Override
    public void deleteByIds(List<Integer> tagIds) {
        if (CollectionUtils.isEmpty(tagIds)) {
            return;
        }

        boolean used = customerTagDao.hasUsed(tagIds);
        if (used) {
            throw new InvalidDataStateException("", "can not delete tags in use");
        }

        customerTagDao.delete(DeleteQuery.of(tagIds).toMap());
    }

    @Override
    public int count(Query query) {
        return customerTagDao.count(query.toMap());
    }

    @Override
    public List<CustomerTag> find(Query query) {
        return customerTagDao.find(query.toMap());
    }

    @Override
    public List<CustomerTag> listAll() {
        return customerTagDao.findAll();
    }

    private CustomerTag buildTag(CustomerTagInfo info) {
        CustomerTag tag = new CustomerTag();
        if (info != null) {
            tag.setId(info.getId());
            tag.setName(info.getName());
            tag.setDescription(info.getDescription());
            tag.setStyleClass(info.getStyleClass());
            tag.setRemark(info.getRemark());

            String userName = SecurityUtil.currentUserName();
            tag.setCreateBy(userName);
            tag.setUpdateBy(userName);
            Date now = new Date();
            tag.setCreateTime(now);
            tag.setUpdateTime(now);
        }
        return tag;
    }
}

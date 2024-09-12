package com.icezhg.sunflower.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.icezhg.sunflower.dao.ContactDao;
import com.icezhg.sunflower.domain.Contact;
import com.icezhg.sunflower.pojo.ContactInfo;
import com.icezhg.sunflower.service.ContactService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhongjibing on 2023/08/13.
 */
@Service
public class ContactServiceImpl implements ContactService {
    private static final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    private final ContactDao contactDao;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public void save(ContactInfo contactInfo) {
        log.info("contact info: {}", JSONObject.toJSONString(contactInfo));
        boolean saved = listCurrentUserAllContacts().stream().anyMatch(contact -> isSameContact(contact, contactInfo));
        log.info("isSaved: {}", saved);
        if (!saved) {
            try {
                contactDao.insert(buildContact(contactInfo));
            } catch (Exception e) {
                log.error("create contact error", e);
                throw e;
            }
        }
    }

    @Override
    public List<ContactInfo> listAll() {
        return listCurrentUserAllContacts().stream().map(this::buildContactInfo).collect(Collectors.toList());
    }

    private List<Contact> listCurrentUserAllContacts() {
        return contactDao.find(Map.of("openid", SecurityUtil.currentUserName()));
    }

    private ContactInfo buildContactInfo(Contact contact) {
        ContactInfo info = new ContactInfo();
        info.setName(contact.getName());
        info.setMobile(contact.getMobile());
        return info;
    }

    private Contact buildContact(ContactInfo info) {
        Contact contact = new Contact();
        contact.setName(info.getName());
        contact.setMobile(info.getMobile());
        contact.setOpenid(SecurityUtil.currentUserName());
        contact.setCreateTime(new Date());
        contact.setUpdateTime(new Date());
        return contact;
    }

    private boolean isSameContact(Contact contact, ContactInfo contactInfo) {
        return StringUtils.equals(contact.getName(), contactInfo.getName())
                && StringUtils.equals(contact.getMobile(), contactInfo.getMobile());
    }
}

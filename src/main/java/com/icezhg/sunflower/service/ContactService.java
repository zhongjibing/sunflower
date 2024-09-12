package com.icezhg.sunflower.service;

import com.icezhg.sunflower.pojo.ContactInfo;

import java.util.List;

/**
 * Created by zhongjibing on 2023/08/13.
 */
public interface ContactService {

    void save(ContactInfo contactInfo);

    List<ContactInfo> listAll();
}

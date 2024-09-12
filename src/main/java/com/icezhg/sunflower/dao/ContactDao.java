package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/08/03
 */
@Repository
public interface ContactDao {

    int insert(Contact record);

    int update(Contact record);

    Contact findById(Long id);

    List<Contact> find(Map<String, Object> param);

}

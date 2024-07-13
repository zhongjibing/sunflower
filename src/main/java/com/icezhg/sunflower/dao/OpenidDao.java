package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.Openid;
import org.springframework.stereotype.Repository;

/**
 * Created by zhongjibing on 2023/07/13.
 */
@Repository
public interface OpenidDao {

    Openid findByOpenid(String openid);

    int insert(Openid record);

}

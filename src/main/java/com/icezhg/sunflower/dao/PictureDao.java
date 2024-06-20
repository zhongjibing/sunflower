package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2022/09/10.
 */
@Repository
public interface PictureDao {

    Picture findById(String id);

    int deleteById(String id);

    int insert(Picture record);

    int update(Picture record);

    int count(Map<String, Object> query);

    List<Picture> find(Map<String, Object> query);

    Picture findByAvatar(String avatar);
}

package com.icezhg.sunflower.service;


import com.icezhg.sunflower.domain.Picture;
import com.icezhg.sunflower.pojo.query.NameQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by zhongjibing on 2022/09/11.
 */
public interface PictureService {

    Picture findById(String id);

    int deleteById(String id);

    Picture save(MultipartFile file);

    Picture update(Picture record);

    int count(NameQuery query);

    List<Picture> find(NameQuery query);

    Picture findByAvatar(String avatar);

}

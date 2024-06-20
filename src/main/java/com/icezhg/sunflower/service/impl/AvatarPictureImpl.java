package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.AvatarPictureDao;
import com.icezhg.sunflower.domain.AvatarPicture;
import com.icezhg.sunflower.service.AvatarPictureService;
import org.springframework.stereotype.Service;

/**
 * Created by zhongjibing on 2023/06/20.
 */
@Service
public class AvatarPictureImpl implements AvatarPictureService {

    private final AvatarPictureDao avatarPictureDao;

    public AvatarPictureImpl(AvatarPictureDao avatarPictureDao) {
        this.avatarPictureDao = avatarPictureDao;
    }

    @Override
    public void create(AvatarPicture avatarPicture) {
        avatarPictureDao.create(avatarPicture);
    }

    @Override
    public void update(AvatarPicture avatarPicture) {
        avatarPictureDao.update(avatarPicture);
    }
}

package com.icezhg.sunflower.service;


import com.icezhg.sunflower.domain.AvatarPicture;
import com.icezhg.sunflower.pojo.Profile;
import com.icezhg.sunflower.pojo.ProfilePasswd;

/**
 * Created by zhongjibing on 2022/09/14.
 */
public interface ProfileService {

    Profile buildProfile();

    void updatePasswd(ProfilePasswd profilePasswd);

    Profile updateProfile(Profile profile);

    void updateAvatar(AvatarPicture avatarPicture);
}

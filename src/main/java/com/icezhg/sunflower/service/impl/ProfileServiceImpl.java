package com.icezhg.sunflower.service.impl;


import com.icezhg.sunflower.dao.AvatarPictureDao;
import com.icezhg.sunflower.dao.RoleDao;
import com.icezhg.sunflower.dao.UserDao;
import com.icezhg.sunflower.domain.AvatarPicture;
import com.icezhg.sunflower.domain.Role;
import com.icezhg.sunflower.domain.User;
import com.icezhg.sunflower.pojo.Profile;
import com.icezhg.sunflower.pojo.ProfilePasswd;
import com.icezhg.sunflower.service.ProfileService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by zhongjibing on 2022/09/14.
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final AvatarPictureDao avatarPictureDao;

    private PasswordEncoder passwordEncoder;

    public ProfileServiceImpl(UserDao userDao, RoleDao roleDao, AvatarPictureDao avatarPictureDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.avatarPictureDao = avatarPictureDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Profile buildProfile() {
        Profile profile = new Profile();
        User user = userDao.findUserById(SecurityUtil.currentUserId());
        profile.setUsername(user.getUsername());
        profile.setNickname(user.getNickname());
        profile.setGender(user.getGender());
        profile.setBirthdate(user.getBirthdate());
        profile.setMobile(user.getMobile());
        profile.setEmail(user.getEmail());
        profile.setAvatar(user.getAvatar());
        profile.setCreateTime(user.getCreateTime());

        String roles = roleDao.findAuthRoles(user.getId()).stream()
                .map(Role::getName)
                .collect(Collectors.joining(", "));
        profile.setRoles(roles);
        return profile;
    }

    @Override
    public void updatePasswd(ProfilePasswd profilePasswd) {
        Long userId = SecurityUtil.currentUserId();
        String passwd = userDao.findUserPasswd(userId);
        if (!passwordEncoder.matches(profilePasswd.oldPassword(), passwd)) {
            throw new AccessDeniedException("wrong passwd");
        }

        User user = new User();
        user.setId(userId);
        user.setPassword(passwordEncoder.encode(profilePasswd.newPassword()));
        user.setUpdateTime(new Date());
        user.setUpdateBy(SecurityUtil.currentUserName());
        userDao.update(user);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        User user = new User();
        user.setId(SecurityUtil.currentUserId());
        user.setNickname(profile.getNickname());
        user.setMobile(profile.getMobile());
        user.setEmail(profile.getEmail());
        user.setBirthdate(profile.getBirthdate());
        user.setGender(profile.getGender());
        user.setUpdateTime(new Date());
        user.setUpdateBy(SecurityUtil.currentUserName());
        userDao.update(user);
        return buildProfile();
    }

    @Override
    public void updateAvatar(AvatarPicture avatarPicture) {
        avatarPictureDao.update(avatarPicture);
    }
}

package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.util.ShortUuid;
import com.icezhg.sunflower.common.SysConfig;
import com.icezhg.sunflower.dao.OpenidDao;
import com.icezhg.sunflower.domain.AvatarPicture;
import com.icezhg.sunflower.domain.Openid;
import com.icezhg.sunflower.enums.UserStatus;
import com.icezhg.sunflower.enums.WxRole;
import com.icezhg.sunflower.pojo.BizOpenid;
import com.icezhg.sunflower.pojo.ChangeStatus;
import com.icezhg.sunflower.pojo.OpenidInfo;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.security.UserInfo;
import com.icezhg.sunflower.service.AvatarPictureService;
import com.icezhg.sunflower.service.ConfigService;
import com.icezhg.sunflower.service.OpenidService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongjibing on 2023/07/13.
 */
@Service
public class OpenidServiceImpl implements OpenidService {

    private final OpenidDao openidDao;

    private AvatarPictureService avatarPictureService;


    private ConfigService configService;

    public OpenidServiceImpl(OpenidDao openidDao) {
        this.openidDao = openidDao;
    }

    @Autowired
    public void setAvatarPictureService(AvatarPictureService avatarPictureService) {
        this.avatarPictureService = avatarPictureService;
    }

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    public Openid findByOpenid(String openid) {
        return this.openidDao.findByOpenid(openid);
    }

    @Override
    public Openid create(String openid) {
        AvatarPicture avatarPicture = defaultAvatarPicture();
        avatarPictureService.create(avatarPicture);

        Openid newUser = new Openid();
        newUser.setOpenid(openid);
        newUser.setAvatar(avatarPicture.avatar());
        newUser.setRole(WxRole.USER.getRole());
        newUser.setStatus(UserStatus.NORMAL.getStatus());
        newUser.setCode(ShortUuid.random());
        newUser.setCreateTime(new Date());
        newUser.setUpdateTime(new Date());
        this.openidDao.insert(newUser);
        return newUser;
    }

    @Override
    public void save(Openid openid) {
        this.openidDao.insert(openid);
    }

    @Override
    public Openid findById(Long id) {
        return this.openidDao.findById(id);
    }

    @Override
    public int count(Query query) {
        return this.openidDao.count(query.toMap());
    }

    @Override
    public List<Openid> find(Query query) {
        return this.openidDao.find(query.toMap());
    }

    @Override
    public int changeStatus(ChangeStatus change) {
        Openid openid = new Openid();
        openid.setId(change.getId());
        openid.setStatus(change.getStatus());
        openid.setUpdateTime(new Date());
        return this.openidDao.update(openid);
    }

    @Override
    public OpenidInfo update(OpenidInfo info) {
        this.openidDao.update(buildOpenid(info));
        return buildOpenidInfo(findById(info.getId()));
    }

    @Override
    public void update(BizOpenid info) {
        UserInfo userInfo = SecurityUtil.currentUserInfo();
        if (StringUtils.isNotBlank(info.getAvatar())) {
            AvatarPicture avatarPicture = new AvatarPicture(userInfo.getPicture(), info.getAvatar());
            avatarPictureService.update(avatarPicture);
        }

        Openid openid = new Openid();
        openid.setId(SecurityUtil.currentUserId());
        openid.setNickname(info.getNickname());
        openid.setUpdateTime(new Date());
        this.openidDao.update(openid);
    }

    @Override
    public void updateLastLoginTime(String openid) {
        this.openidDao.updateLastLoginTime(openid, new Date());
    }

    @Override
    public void updateUid(Long id, String uid) {
        this.openidDao.updateUid(id, uid);
    }

    private AvatarPicture defaultAvatarPicture() {
        return new AvatarPicture(configService.findConfig(SysConfig.DEFAULT_AVATAR_PICTURE));
    }

    private Openid buildOpenid(OpenidInfo info) {
        Openid openid = new Openid();
        openid.setId(info.getId());
        openid.setNickname(info.getNickname());
        openid.setMobile(info.getMobile());
        openid.setAvatar(info.getAvatar());
        openid.setRole(info.getRole());
        openid.setStatus(info.getStatus());
        openid.setRemark(info.getRemark());
        openid.setUpdateTime(new Date());
        return openid;
    }

    private OpenidInfo buildOpenidInfo(Openid openid) {
        OpenidInfo info = new OpenidInfo();
        if (openid != null) {
            info.setId(openid.getId());
            info.setNickname(openid.getNickname());
            info.setMobile(openid.getMobile());
            info.setAvatar(openid.getAvatar());
            info.setRole(openid.getRole());
            info.setStatus(openid.getStatus());
            info.setCode(openid.getCode());
            info.setUid(openid.getUid());
            info.setCreateTime(openid.getCreateTime());
            info.setUpdateTime(openid.getUpdateTime());
            info.setRemark(openid.getRemark());
            info.setLastLoginTime(openid.getLastLoginTime());
        }
        return info;
    }
}

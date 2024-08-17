package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.util.ShortUuid;
import com.icezhg.sunflower.dao.InvitationDao;
import com.icezhg.sunflower.domain.Invitation;
import com.icezhg.sunflower.domain.Openid;
import com.icezhg.sunflower.service.InvitationService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/08/07.
 */
@Service
public class InvitationServiceImpl implements InvitationService {

    private final InvitationDao invitationDao;

    public InvitationServiceImpl(InvitationDao invitationDao) {
        this.invitationDao = invitationDao;
    }


    @Override
    public void create(Openid openid) {
        Invitation invitation = new Invitation();
        invitation.setUserId(openid.getId());
        invitation.setOpenid(openid.getOpenid());
        invitation.setCode(ShortUuid.random());
        invitation.setCreateTime(openid.getCreateTime());
        invitation.setUpdateTime(openid.getUpdateTime());
        invitationDao.insert(invitation);
    }

    @Override
    public void updateInviterCode(String inviterCode) {
        if (StringUtils.isBlank(inviterCode)) {
            return;
        }

        Long userId = SecurityUtil.currentUserId();
        String openid = SecurityUtil.currentUserName();
        Invitation invitation = invitationDao.findByUserIdAndOpenid(userId, openid);
        if (invitation == null) {
            invitation = new Invitation();
            invitation.setUserId(userId);
            invitation.setOpenid(openid);
            invitation.setCode(ShortUuid.random());
            invitation.setCreateTime(new Date());
            invitation.setUpdateTime(new Date());
            invitationDao.insert(invitation);
        }

        invitation.setInviterCode(inviterCode);
        invitation.setUpdateTime(new Date());
        invitationDao.update(invitation);
    }
}

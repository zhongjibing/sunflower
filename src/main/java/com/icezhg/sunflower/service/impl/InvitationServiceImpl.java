package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.InvitationDao;
import com.icezhg.sunflower.domain.Invitation;
import com.icezhg.sunflower.security.UserDetail;
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
    public void updateInviterCode(String inviterCode) {
        if (StringUtils.isBlank(inviterCode)) {
            return;
        }

        Long userId = SecurityUtil.currentUserId();
        UserDetail userDetail = SecurityUtil.currentUser();
        Invitation invitation = new Invitation();
        invitation.setUserId(userId);
        invitation.setOpenid(userDetail.getOpenid());
        invitation.setCode(userDetail.getCode());
        invitation.setInviterCode(inviterCode);
        invitation.setCreateTime(new Date());
        invitation.setUpdateTime(new Date());
        invitationDao.insert(invitation);
    }
}

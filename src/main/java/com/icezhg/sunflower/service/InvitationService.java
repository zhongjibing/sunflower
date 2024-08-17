package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.Openid;

/**
 * Created by zhongjibing on 2023/08/07.
 */
public interface InvitationService {

    void create(Openid openid);

    void updateInviterCode(String inviterCode);
}

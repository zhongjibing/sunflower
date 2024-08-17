package com.icezhg.sunflower.event.listener;

import com.icezhg.sunflower.event.OpenUserRegisterEvent;
import com.icezhg.sunflower.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2023/08/07.
 */
@Component
public class OpenUserRegisterEventListener implements ApplicationListener<OpenUserRegisterEvent> {

    private InvitationService invitationService;

    @Autowired
    public void setInvitationService(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @Override
    public void onApplicationEvent(OpenUserRegisterEvent event) {
        invitationService.create(event.getOpenId());
    }
}

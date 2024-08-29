package com.icezhg.sunflower.controller.business;

import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.service.InvitationService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2023/08/07.
 */
@RestController
@RequestMapping("/business/invitation")
public class invitationController {

    private InvitationService invitationService;

    public invitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PutMapping
    @Secured(Authority.Wx.USER)
    public void update(String code) {
        invitationService.updateInviterCode(code);
    }
}

package com.icezhg.sunflower.controller.business;

import com.icezhg.sunflower.service.InvitationService;
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
    public void update(String code) {
        invitationService.updateInviterCode(code);
    }
}

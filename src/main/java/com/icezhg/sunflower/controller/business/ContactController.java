package com.icezhg.sunflower.controller.business;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.ContactInfo;
import com.icezhg.sunflower.service.ContactService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhongjibing on 2023/08/13.
 */
@RestController
@RequestMapping("/business/contact")
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PutMapping("/list")
    @Secured(Authority.Wx.USER)
    @Operation(title = "openid user modification", type = OperationType.UPDATE)
    public List<ContactInfo> list() {
        return contactService.listAll();
    }
}

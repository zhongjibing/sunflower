package com.icezhg.sunflower.controller.business;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.BizOpenid;
import com.icezhg.sunflower.service.OpenidService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2023/07/18.
 */
@RestController("businessOpenidController")
@RequestMapping("/business/open")
public class OpenidController {

    private final OpenidService openidService;

    public OpenidController(OpenidService openidService) {
        this.openidService = openidService;
    }

    @PutMapping("/update")
    @Secured(Authority.Wx.USER)
    @Operation(title = "openid user modification", type = OperationType.UPDATE)
    public void edit(@Validated @RequestBody BizOpenid info) {
        this.openidService.update(info);
    }
}

package com.icezhg.sunflower.controller.business;

import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.service.OptionTypeService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2023/08/16.
 */
@RestController
@RequestMapping("/business/options")
public class OptionsController {

    private OptionTypeService optionTypeService;

    public OptionsController(OptionTypeService optionTypeService) {
        this.optionTypeService = optionTypeService;
    }

    @GetMapping("/list")
    @Secured(Authority.Wx.USER)
    public Object collectAll() {
        return optionTypeService.collectAll();
    }
}

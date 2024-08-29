package com.icezhg.sunflower.controller.business;

import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2023/07/26.
 */
@RestController
@RequestMapping("/business/resource")
public class ResourceController {

    protected ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/banquet/all")
    @Secured(Authority.Wx.USER)
    public Object banquets() {
        return resourceService.listResourcePriceInfoAll(ResourceType.BANQUET_HALL);
    }

    @GetMapping("/conference/all")
    @Secured(Authority.Wx.USER)
    public Object conferences() {
        return resourceService.listResourcePriceInfoAll(ResourceType.CONFERENCE_ROOM);
    }

    @GetMapping("/room/all")
    @Secured(Authority.Wx.USER)
    public Object rooms() {
        return resourceService.listResourcePriceInfoAll(ResourceType.GUEST_ROOM);
    }
}

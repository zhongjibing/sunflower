package com.icezhg.sunflower.controller.system;

import com.icezhg.sunflower.pojo.Router;
import com.icezhg.sunflower.service.RouterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhongjibing on 2022/09/20.
 */
@RestController
@RequestMapping("/routers")
public class RouterController {

    private final RouterService routerService;

    public RouterController(RouterService routerService) {
        this.routerService = routerService;
    }

    @GetMapping
    public List<Router> routers() {
        return routerService.listRouters();
    }
}

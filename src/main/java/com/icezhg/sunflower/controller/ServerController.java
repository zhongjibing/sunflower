package com.icezhg.sunflower.controller;


import com.icezhg.sunflower.server.Server;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2022/09/03.
 */
@RestController
@RequestMapping("/server")
public class ServerController {

    @GetMapping
    public Object serverInfo(String name) {
        return StringUtils.hasText(name) ? Server.getInfo(name) : Server.getInfo(Server.ALL);
    }
}

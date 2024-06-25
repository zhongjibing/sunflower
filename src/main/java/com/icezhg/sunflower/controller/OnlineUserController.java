package com.icezhg.sunflower.controller;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.OnlineUserQuery;
import com.icezhg.sunflower.service.OnlineUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/online")
public class OnlineUserController {

    private final OnlineUserService onlineUserService;

    public OnlineUserController(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @GetMapping("/list")
    @Operation(title = "online users list", type = OperationType.QUERY, saveResult = false)
    public PageResult list(OnlineUserQuery query) {
        return onlineUserService.listOnlineUsers(query);
    }




}

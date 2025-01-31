package com.icezhg.sunflower.controller.monitor;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.OnlineUserQuery;
import com.icezhg.sunflower.service.OnlineUserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Secured(Authority.Monitor.Online.QUERY)
    @Operation(title = "online users list", type = OperationType.QUERY, saveResult = false)
    public PageResult list(OnlineUserQuery query) {
        return onlineUserService.listOnlineUsers(query);
    }

    @DeleteMapping("/{id}")
    @Secured(Authority.Monitor.Online.LOGOUT)
    public void forceLogout(@PathVariable String id) {
        onlineUserService.forceLogout(id);
    }


}

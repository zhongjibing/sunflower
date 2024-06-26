package com.icezhg.sunflower.controller;

import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.LoginQuery;
import com.icezhg.sunflower.service.LoginRecordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/records")
public class LoginRecordController {

    private final LoginRecordService loginRecordService;

    public LoginRecordController(LoginRecordService loginRecordService) {
        this.loginRecordService = loginRecordService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('monitor:logininfo:list')")
    public PageResult listLogs(LoginQuery query) {
        return new PageResult(loginRecordService.count(query), loginRecordService.find(query));
    }
}

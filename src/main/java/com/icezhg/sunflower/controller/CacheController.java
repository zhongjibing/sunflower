package com.icezhg.sunflower.controller;


import com.icezhg.sunflower.common.Authority;

import com.icezhg.sunflower.service.CacheService;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping
    @Secured(Authority.Monitor.Cache.QUERY)
    public Object getInfo() {
        return cacheService.getCacheInfo();
    }
}

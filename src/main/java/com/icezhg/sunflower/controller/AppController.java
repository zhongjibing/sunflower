package com.icezhg.sunflower.controller;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhongjibing on 2023/06/17.
 */
@RestController
@RequestMapping
public class AppController {

    private List<Filter> filters;

    private final AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    @GetMapping("/hello")
    public Object hello() {
        if (counter.getAndIncrement() % 2 > 0) {
            throw new IllegalArgumentException("test error");
        }
        return "hello, world!";
    }
}

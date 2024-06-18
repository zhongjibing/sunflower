package com.icezhg.sunflower.controller;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhongjibing on 2023/06/17.
 */
@RestController
@RequestMapping
public class AppController {

    private List<Filter> filters;

    @Autowired
    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    @GetMapping("/hello")
    public Object hello() {
        return "hello, world!";
    }
}

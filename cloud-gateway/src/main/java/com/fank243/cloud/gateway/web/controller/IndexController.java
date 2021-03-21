package com.fank243.cloud.gateway.web.controller;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("")
public class IndexController {

    @Value("${test.name:}")
    private String testName;

    @SentinelResource
    @GetMapping("/hello")
    public String hello() {
        System.out.println(testName);
        return "hello";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}

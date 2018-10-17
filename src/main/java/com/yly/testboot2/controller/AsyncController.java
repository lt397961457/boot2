package com.yly.testboot2.controller;

import com.yly.testboot2.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class AsyncController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/first")
    public Boolean fistAsync() throws InterruptedException {
        logger.info("/first 请求进入");
        asyncService.method1();
        logger.info("/first 请求结束");
        return true;
    }
}

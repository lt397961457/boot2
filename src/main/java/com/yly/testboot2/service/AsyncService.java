package com.yly.testboot2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 测试 异步调用 @Async
 */
@Component
public class AsyncService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Async
    public void method1() throws InterruptedException {
        logger.info("进入 method1======");
        Thread.sleep(4000);
        logger.info("method1调用结束");
    }
}

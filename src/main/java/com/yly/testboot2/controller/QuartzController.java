package com.yly.testboot2.controller;

import com.yly.testboot2.entity.GoodInfoEntity;
import com.yly.testboot2.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quartz")
public class QuartzController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/savegood")
    public Long saveGood(@RequestBody GoodInfoEntity good) throws Exception {
        logger.info("保存商品==========》"+good);
        return goodsService.saveGood(good);
    }
}

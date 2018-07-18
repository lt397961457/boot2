package com.yly.testboot2.controller;

import com.yly.testboot2.service.DataServiceWithLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lock/zk")
public class ZkLockController {
    @Autowired
    private DataServiceWithLock dataServiceWithLock;
    @GetMapping("/getlockeddata")
    public Integer getData(@RequestParam(value = "value") Integer value){
        return dataServiceWithLock.getDataWithZK(value);
    }
}

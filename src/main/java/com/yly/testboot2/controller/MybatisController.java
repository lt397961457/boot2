package com.yly.testboot2.controller;

import com.yly.testboot2.entity.UserEntity;
import com.yly.testboot2.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/mybatis")
public class MybatisController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public UserEntity getByUserID(@PathVariable(value = "id") Integer userID){
        logger.info("MybatisController-->getByUserID:id="+userID);
        return userMapper.getOne(userID);
    }
    @GetMapping("/allUser")
    public List<UserEntity> getAllUser(){
        logger.info("MybatisController->getAllUser");
        return userMapper.getAll();
    }


}

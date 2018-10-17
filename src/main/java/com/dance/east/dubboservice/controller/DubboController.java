package com.dance.east.dubboservice.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dance.east.dubboservice.entity.City;
import com.dance.east.dubboservice.service.DanceDubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Reference(version = "1.0.0")
    private DanceDubboService danceDubboService;
    @GetMapping("/getcity")
    public City getCity(){
        City city = danceDubboService.getCityById(1);
        return city;
    }
    @GetMapping("/getcityname")
    public String getCityByName(){
        String name = danceDubboService.findCityNameById(1);
        return name;
    }
}

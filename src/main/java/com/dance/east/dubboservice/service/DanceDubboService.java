package com.dance.east.dubboservice.service;

import com.dance.east.dubboservice.entity.City;

public interface DanceDubboService {
    public String findCityNameById(int id);
    public City getCityById(int id);
}

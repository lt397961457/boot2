package com.dance.east.dubboservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class City implements Serializable {
    private static final long serialVersionUID = -1784735164791748036L;
    private String cityName;
    private int id;
}

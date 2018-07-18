package com.yly.testboot2.entity;

import lombok.Data;

import java.util.Date;

@Data
public class KafkaMessage {
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳

}
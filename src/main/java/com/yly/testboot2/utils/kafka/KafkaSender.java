package com.yly.testboot2.utils.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yly.testboot2.entity.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class KafkaSender {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send(String topic,KafkaMessage message) {
        message.setSendTime(new Date());
        logger.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        kafkaTemplate.send(topic, gson.toJson(message));
    }
}
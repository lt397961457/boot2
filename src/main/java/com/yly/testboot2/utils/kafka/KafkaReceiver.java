package com.yly.testboot2.utils.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;



@Component
public class KafkaReceiver {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @KafkaListener(topics = "${app.topic.iptv}")
    public void receive(@Payload String message,
                        @Headers MessageHeaders headers) {
        logger.info("received message='{}'", message);
        headers.keySet().forEach(key -> logger.info("{}: {}", key, headers.get(key)));
    }
}
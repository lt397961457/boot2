package com.yly.testboot2.controller;

import com.yly.testboot2.entity.KafkaMessage;
import com.yly.testboot2.utils.kafka.KafkaReceiver;
import com.yly.testboot2.utils.kafka.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaSender kafkaSender;
    @Autowired
    private KafkaReceiver kafkaReceiver;
    /**
     * 向kafka 的 指定的 topic 发送消息
     */
    @PostMapping("/send/{topic}")
    public Boolean sendMessage(@PathVariable(value = "topic") String topic, @RequestBody KafkaMessage message){
        try {

            kafkaSender.send(topic,message);
            return true;
        }catch (Exception e){
            logger.info(e.getMessage());
            return false;
        }
    }
    @GetMapping("/recive/{topic}")
    public KafkaMessage getMessage(@RequestParam(value = "topic") String topic){
        return null;
    }

}

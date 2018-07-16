package com.yly.testboot2.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class PropertiesUtil {
    @Value("${spring.application.name}")
    private  String appName;

    @Value("${server.port}")
    private  String port;
}

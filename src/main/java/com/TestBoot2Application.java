package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication/*(exclude = {
		DataSourceAutoConfiguration.class
})*/
@EnableKafka
//允许异步调用
@EnableAsync
public class TestBoot2Application {
	public static void main(String[] args) {
		SpringApplication.run(TestBoot2Application.class, args);
	}
}

package com.yly.testboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class TestBoot2Application {
	public static void main(String[] args) {
		SpringApplication.run(TestBoot2Application.class, args);
	}
}

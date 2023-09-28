package com.monthu.recode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RecodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecodeApplication.class, args);
    }

}


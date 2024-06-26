package com.shop.springentitytest;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class SpringEntityTestApplication {
    public static void main(String[] args) {

        SpringApplication.run(SpringEntityTestApplication.class, args);
    }

}

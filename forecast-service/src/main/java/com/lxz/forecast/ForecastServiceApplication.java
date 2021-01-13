package com.lxz.forecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ForecastServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForecastServiceApplication.class, args);
    }

}

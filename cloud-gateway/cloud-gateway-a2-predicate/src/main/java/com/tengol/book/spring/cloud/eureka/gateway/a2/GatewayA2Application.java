package com.tengol.book.spring.cloud.eureka.gateway.a2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * GatewayA2Application
 *
 * @author dongrui
 * @date 2019/11/15 14:30
 */
@Slf4j
@SpringBootApplication
public class GatewayA2Application {
    public static void main(String[] args) {
        SpringApplication.run(GatewayA2Application.class, args);
        log.info("start successfully ...");
    }
}

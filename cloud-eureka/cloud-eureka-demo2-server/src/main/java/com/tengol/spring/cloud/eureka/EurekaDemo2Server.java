package com.tengol.spring.cloud.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EurekaDemo2Server
 *
 * @author dongrui
 * @date 2019/12/19 16:54
 */
@Slf4j
@SpringBootApplication
@EnableEurekaServer
public class EurekaDemo2Server {
    public static void main(String[] args) {
        SpringApplication.run(EurekaDemo2Server.class, args);
        log.info("EurekaDemo2Server start successfully ...");
    }
}

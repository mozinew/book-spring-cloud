package com.tengol.spring.cloud.eureka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * ConfigServerApplication
 *
 * @author dongrui
 * @date 2019/12/19 16:33
 */
@Slf4j
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
        log.info("Config Server start successfully ...");
    }
}

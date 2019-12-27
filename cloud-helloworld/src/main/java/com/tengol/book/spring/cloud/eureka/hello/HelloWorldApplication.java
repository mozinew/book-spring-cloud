package com.tengol.book.spring.cloud.eureka.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * HelloWorldApplication
 *
 * @author dongrui
 * @date 2019/11/15 17:34
 */
@Slf4j
@SpringBootApplication
public class HelloWorldApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
        log.info("start successfully ...");
    }
}

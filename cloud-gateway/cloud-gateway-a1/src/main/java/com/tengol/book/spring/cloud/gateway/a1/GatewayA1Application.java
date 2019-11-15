package com.tengol.book.spring.cloud.gateway.a1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * GatewayA1Application
 *
 * @author dongrui
 * @date 2019/11/15 10:58
 */
@Slf4j
@SpringBootApplication
public class GatewayA1Application {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/jd").uri("http://www.jd.com:80").id("jd_route"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayA1Application.class, args);
        log.info("start successfully !");
    }
}

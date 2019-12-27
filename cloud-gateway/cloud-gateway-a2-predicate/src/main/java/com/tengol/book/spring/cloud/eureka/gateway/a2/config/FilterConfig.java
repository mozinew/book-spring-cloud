package com.tengol.book.spring.cloud.eureka.gateway.a2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FilterConfig
 *
 * @author dongrui
 * @date 2019/11/20 13:00
 */
@Slf4j
@Configuration
public class FilterConfig {
    @Bean
    public RouteLocator rewritePathFilter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("rewrite_path_route",
                        r -> r.path("/abc/**")
                        .filters(f -> f.rewritePath("/abc/(?<segment>^/)", "/$\\{segment}"))
                        .uri("http://localhost:8081/test/")
                )
                .build();
    }

    /**
     * AddRequest
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator addRequestHeaderFilter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("add_request_header_route", r -> r.path("/testFilter").filters(
                        f -> f.addRequestHeader("x-app-code", "ni-spf")
                ).uri("http://localhost:8081/test/header"))
                .build();
    }
}

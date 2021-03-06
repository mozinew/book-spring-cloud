package com.tengol.book.spring.cloud.eureka.gateway.a2.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 配置 路由断言工厂
 * Spring Cloud Gateway 常用的路由断言工厂：
 * 1. After 路由断言工厂 ： 大于设定的 UTC 时间时会成功匹配，否则不能成功匹配
 * 2. Before 路由断言工厂 ： 小于设定的 UTC 时间时会成功匹配，否则不能成功匹配
 * 3. Between 路由断言工厂 ：
 *
 * @author dongrui
 * @date 2019/11/15 14:32
 */
@Slf4j
@Configuration
public class RoutePredicateFactoryConfig {
    /**
     * RemoteAddr 路由断言工厂
     * 1. 规则：当请求 IP 地址在网段之内或者和配置的 IP 相同，则匹配成功，否则不会成功匹配
     * 2. 期待：当访问 http://127.0.0.1:8080 时跳转到京东首页，否则不会成功匹配，也不会转发
     */
    @Bean
    public RouteLocator remoteAddrPredicate(RouteLocatorBuilder builder){
        return builder.routes()
                .route("remote_addr_route", r->r.remoteAddr("127.0.0.1").uri("http://www.jd.com"))
                .build();
    }

    /**
     * Query 路由断言工厂
     * 1. 规则：当请求参数中包含路由规则配置的参数（）时成功匹配，否则不会成功匹配
     * 2. 期待：当请求方法包含参数 hello=world 时成功跳转到百度首页，否则不会匹配
     */
    @Bean
    public RouteLocator queryPredicate(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("query_route", r -> r.query("hello", "world").uri("http://www.baidu.com"))
                .build();
    }

    /**
     * Method 路由断言工厂
     * 1. 规则：当请求方法 (GET/POST) 与规则配置的方法相同时会成功匹配，否则不会匹配
     * 2. 期待：当请求方法为 GET 时跳转到京东首页，否则不跳转
     */
    //@Bean
    public RouteLocator methodPredicate(RouteLocatorBuilder builder) {
        RouteLocator methodRoute = builder.routes()
                .route("method_route", r -> r.method("GET").uri("http://www.baidu.com"))
                .build();
        log.info("Method 路由断言工厂启动：{}", JSON.toJSONString(methodRoute));

        return methodRoute;
    }

    /**
     * Host 路由断言工厂
     * 1. 规则：当请求 Host 包含在指定的 Host 配置规则中时成功匹配，否则不会匹配
     * 2. 期待：配置 Host ( 127.0.0.1 vip.baidu.com )，访问 http://vip.baidu.com:8090 时跳转到京东首页，否则不跳转且报错404
     */
    @Bean
    public RouteLocator hostPredicate(RouteLocatorBuilder builder) {
        RouteLocator hostRoute = builder.routes().route("host_route", r -> r.host("*.baidu.com:*").uri("http://www.jd.com")).build();
        log.info("Host 路由断言工厂启动：{}", JSON.toJSONString(hostRoute));

        return hostRoute;
    }

    /**
     * Header 路由断言工厂
     * 1. 规则：根据配置的路由 header 信息进行断言匹配路由，匹配成功则进行转发，否则不进行转发
     * 2. 预期：当 header 包含 x-app-code=spf 时跳转到 /test/header 请求，返回结果为 spf，否则报错 404
     */
    @Bean
    public RouteLocator headerPredicate(RouteLocatorBuilder builder) {
        RouteLocator headerRoute = builder.routes()
                .route("header_route", r -> r.header("x-app-code", "spf").uri("http://localhost:8081/test/header"))
                .build();
        log.info("Header 路由断言工厂启动：{}", JSON.toJSONString(headerRoute));

        return headerRoute;
    }

    /**
     * Cookie 路由断言工厂
     * 1. 规则：当请求的 Cookie 中包含指定的 Cookie 键值对时会成功匹配，否则不能匹配
     * 2. 预期：cookie 包含 hello=world 时跳转到指定URL，否则不跳转
     */
    @Bean
    public RouteLocator cookiePredicate(RouteLocatorBuilder builder) {
        RouteLocator cookieRoute = builder.routes()
                .route("cookie_route", r -> r.cookie("hello", "world").uri("http://localhost:8081/test/cookie"))
                .build();
        log.info("Cookie 路由断言工厂启动：{}", JSON.toJSONString(cookieRoute));

        return cookieRoute;
    }

    /**
     * Between 路由断言工厂
     * 1. 规则：如果请求进来时的时间在设置的开始和结束时间范围内，则会成功匹配，否则不会成功匹配
     * 2. 预期：启动后的1分钟内访问失败，1分钟到2分钟时访问正常，2分钟后访问失败
     */
    //@Bean
    public RouteLocator betweenPredicate(RouteLocatorBuilder builder) {
        // 生成比当前时间晚 1 分钟的 UTC 时间
        ZonedDateTime start = LocalDateTime.now().plusMinutes(1L).atZone(ZoneId.systemDefault());
        // 生成比当前时间晚 2 分钟的 UTC 时间
        ZonedDateTime end = LocalDateTime.now().plusMinutes(2L).atZone(ZoneId.systemDefault());
        //生成路由规则
        RouteLocator between_route = builder.routes()
                .route("between_route", r -> r.between(start, end).uri("http://www.baidu.com:80"))
                .build();
        log.info("Between 路由断言工厂启动：{}", JSON.toJSONString(between_route));

        return between_route;
    }


    /**
     * Before 路由断言工厂
     * 1. 规则：该工厂会取一个 UTC 时间格式的时间参数，当请求的时间在设置的 UTC 时间之前时会成功匹配，否则，不能成功匹配。
     */
    //@Bean
    public RouteLocator beforePredicate(RouteLocatorBuilder builder) {
        // 生成比当前时间早一小时的 UTC 时间
        ZonedDateTime minusTime = LocalDateTime.now().minusHours(1L).atZone(ZoneId.systemDefault());
        // 生成比当前时间晚一小时的 UTC 时间
        ZonedDateTime plusTime = LocalDateTime.now().plusMinutes(1L).atZone(ZoneId.systemDefault());
        //增加一个 After 路由断言工厂
        RouteLocator beforeRoute = builder.routes()
                .route("before_route", r -> r.before(plusTime).uri("http://www.jd.com"))
                .build();
        log.info("Before 路由断言工厂: {}", JSON.toJSONString(beforeRoute));

        return beforeRoute;
    }


    /**
     * After 路由断言工厂
     * 1. 规则：该工厂会取一个 UTC 时间格式的时间参数，当请求进来的当前时间在配置的 UTC 时间之后，则会匹配成功，否则匹配失败 。
     * 2. 预期：1 分钟内访问失败，过 1 分钟后再次访问 http://localhost:8080 会跳转到百度首页，
     */
    //@Bean
    public RouteLocator afterPredicate(RouteLocatorBuilder builder) {
        // 生成比当前时间早一小时的 UTC 时间 （用于正确演示）
        ZonedDateTime minusTime = LocalDateTime.now().minusMinutes(1L).atZone(ZoneId.systemDefault());
        // 生成比当前时间晚一小时的 UTC 时间 （用于错误演示）
        ZonedDateTime plusTime = LocalDateTime.now().minusMinutes(1L).atZone(ZoneId.systemDefault());
        //增加一个 After 路由断言工厂
        RouteLocator afterRoute = builder.routes()
                .route("after_route", r -> r.after(plusTime).uri("http://www.baidu.com"))
                //.route("after_route", r -> r.after(plusTime).uri("http://www.baidu.com"))
                .build();
        log.info("After 路由断言工厂: {}", JSON.toJSONString(afterRoute));

        return afterRoute;
    }
}

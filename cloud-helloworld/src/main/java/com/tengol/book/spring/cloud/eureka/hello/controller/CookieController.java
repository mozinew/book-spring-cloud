package com.tengol.book.spring.cloud.eureka.hello.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * CookieController
 *
 * @author dongrui
 * @date 2019/11/15 17:36
 */
@RestController
public class CookieController {
    @GetMapping("/test/hello")
    public ResponseEntity<String> helloWorld(HttpServletRequest request) {
        String name = request.getParameter("name");
        return ResponseEntity.ok(JSON.toJSONString(name));
    }

    @GetMapping("/test/cookie")
    public ResponseEntity<String> testCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        return ResponseEntity.ok(JSON.toJSONString(cookies));
    }

    @GetMapping("/test/header")
    public ResponseEntity<String> testHeader(HttpServletRequest request) {
        String header = request.getHeader("x-app-code");
        return ResponseEntity.ok(header);
    }
}

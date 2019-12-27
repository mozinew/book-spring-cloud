package com.tengol.book.spring.cloud.eureka.hello.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * HelloController
 *
 * @author dongrui
 * @date 2019/11/25 17:16
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public ResponseEntity<String> sayHello1(@RequestParam("name") String name) {
        System.out.println("sayHello1");
        return ResponseEntity.ok("Hello " + name);
    }

    @GetMapping("/")
    public ResponseEntity<String> sayHello2(@RequestParam("name") String name) {
        System.out.println("sayHello2");
        return ResponseEntity.ok("Hello " + name);
    }

    @PostMapping
    public ResponseEntity<String> sayHello3(@RequestParam("name") String name) {
        System.out.println("sayHello3");
        return ResponseEntity.ok("Hello " + name);
    }
}

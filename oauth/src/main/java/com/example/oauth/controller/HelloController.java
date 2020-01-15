package com.example.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenhua zhang
 * @data 2020/1/6
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public Object hello(@RequestParam String str) {
        System.out.println("###############" + str + "###############");
        return str;
    }
}

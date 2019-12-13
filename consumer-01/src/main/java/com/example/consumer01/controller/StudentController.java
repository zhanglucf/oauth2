package com.example.consumer01.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenhua zhang
 * @data 2019/12/10
 */
@RequestMapping("/st")
@RestController
public class StudentController {

    @Reference(check = false,lazy = true,timeout = 30000)
    private StudentRpcService studentRPCService;



    @GetMapping
    public Student findStudent(@RequestParam Long id) {
        Student student = studentRPCService.findStudent(id);
        return student;
    }
}

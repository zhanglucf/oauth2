package com.example.consumer01.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import support.ResponseResult;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/11
 */
@ResponseResult
@RequestMapping("/article")
@RestController
public class ArticleController {

    @Reference(check = false, lazy = true, timeout = 30000)
    private ArticleRpcService articleRpcService;

    @PostMapping
    Article save(@RequestBody Article article) {
        return articleRpcService.save(article);
    }

    @GetMapping("/{id}")
    Article findById(@PathVariable Long id) {
        return articleRpcService.findById(id);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        articleRpcService.deleteById(id);
    }

    @PutMapping
    Article updateById(@RequestBody Article article) {
        return articleRpcService.updateById(article);
    }

    @GetMapping
    List<Article> getList(@RequestParam Integer current, @RequestParam Integer pageSize) {
        List<Article> list = articleRpcService.getList(current, pageSize);
        return list;
    }

};
package com.example.producer01.service.impl;

import com.example.producer01.service._Provider;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
public class _CatProvider implements _Provider {

    private Class<Cat> support;

    public _CatProvider(Class<Cat> support) {
        this.support = support;
    }

    public Class<Cat> getSupport() {
        return support;
    }

    public void setSupport(Class<Cat> support) {
        this.support = support;
    }

    @Override
    public void desc() {
        System.out.println("猫的速度是蛇的七倍");
    }

    @Override
    public boolean supports(Class clzss) {
        return this.support == clzss;
    }
}

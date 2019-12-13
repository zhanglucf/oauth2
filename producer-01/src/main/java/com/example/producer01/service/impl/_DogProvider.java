package com.example.producer01.service.impl;

import com.example.producer01.service._Provider;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
public class _DogProvider implements _Provider {

    private Class<Dog> support;

    public _DogProvider(Class<Dog> support) {
        this.support = support;
    }

    public Class<Dog> getSupport() {
        return support;
    }

    public void setSupport(Class<Dog> support) {
        this.support = support;
    }

    @Override
    public void desc() {
        System.out.println("狗是人类最真诚的朋友");
    }

    @Override
    public boolean supports(Class clzss) {
        return this.support == clzss;
    }
}

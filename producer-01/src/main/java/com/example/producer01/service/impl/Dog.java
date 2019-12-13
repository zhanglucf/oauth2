package com.example.producer01.service.impl;

import com.example.producer01.service.Animal;
import com.example.producer01.service._AuthenticationManager;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
public class Dog extends AbstractAnimal {
    private _AuthenticationManager authenticationManager;


    public _AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(_AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void eat() {
        System.out.println("狗爱吃骨头");
        authenticationManager.auth(this);
    }
}

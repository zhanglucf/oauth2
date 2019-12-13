package com.example.producer01.service.impl;

import com.example.producer01.service.Animal;
import com.example.producer01.service._AuthenticationManager;
import com.example.producer01.service._Provider;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
public class AuthManager implements _AuthenticationManager {
    private List<_Provider> providers;

    public List<_Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<_Provider> providers) {
        this.providers = providers;
    }

    public AuthManager(List<_Provider> providers) {
        this.providers = providers;
    }

    @Override
    public void auth(Animal animal) {
        Class<? extends Animal> aClass = animal.getClass();
        for (_Provider provider:getProviders()){
            if (provider.supports(aClass)) {
                provider.desc();
            }
        }
    }

}

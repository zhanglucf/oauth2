package com.example.producer01.service.impl;

import com.example.producer01.service.Animal;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
public abstract class AbstractAnimal implements Animal {

    @Override
    public void skill() {
        System.out.println("可爱，跑得快");
        eat();
    }

    protected void eat(){
        System.out.println("爱吃葡萄");
    }
}

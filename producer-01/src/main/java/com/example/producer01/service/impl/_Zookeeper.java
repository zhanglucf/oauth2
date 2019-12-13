package com.example.producer01.service.impl;

import com.example.producer01.service.Animal;
import com.example.producer01.service._AuthenticationManager;
import com.example.producer01.service._Provider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
public class _Zookeeper {

    private List<Animal> animals;

    public _Zookeeper(List<Animal> animals) {
        this.animals = animals;
    }

    public void feedFood() {
        for (Animal animal : animals) {
            animal.skill();
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public static void main(String[] args) {
        //数据准备
        List<Animal> animals = new ArrayList<>();
        List<_Provider> providers = new ArrayList<>();
        providers.add(new _CatProvider(Cat.class));
        providers.add(new _DogProvider(Dog.class));
        AuthManager authManager = new AuthManager(providers);
        Dog dog = new Dog();
        dog.setAuthenticationManager(authManager);
        Cat cat = new Cat();
        cat.setAuthenticationManager(authManager);
        animals.add(dog);
        animals.add(cat);
        _Zookeeper zookeeper = new _Zookeeper(animals);

        zookeeper.feedFood();
    }
}

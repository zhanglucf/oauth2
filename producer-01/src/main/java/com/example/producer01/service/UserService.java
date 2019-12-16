package com.example.producer01.service;

import dto.UserDto;
import entity.User;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
public interface UserService {

    User creatUser(UserDto userDto);

    User findByUserName(String name);

    List<User> findAll();
}

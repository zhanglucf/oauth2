package com.example.producer01.mapper;

import entity.User;
import util.BaseMapper;

public interface UserMapper extends BaseMapper<User> {
    User loadUserByUsername(String userName);
}
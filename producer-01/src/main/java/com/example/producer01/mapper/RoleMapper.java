package com.example.producer01.mapper;

import entity.Role;
import util.BaseMapper;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    Role findByName(String name);

    List<Role> findByUserName(String userName);
}
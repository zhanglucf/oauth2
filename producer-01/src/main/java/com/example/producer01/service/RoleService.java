package com.example.producer01.service;

import entity.Role;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
public interface RoleService {
    List<Role> findRoleByUserName(String userName);
}

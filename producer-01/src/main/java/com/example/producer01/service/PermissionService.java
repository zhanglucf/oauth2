package com.example.producer01.service;

import entity.Permission;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
public interface PermissionService {
    List<Permission> findPermissionByUserName(String userName);
}

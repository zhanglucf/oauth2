package com.example.producer01.service;



import entity.Permission;
import entity.Role;
import entity.User;

import java.util.Set;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
public interface UserRpcService {

    User loadUserByUsername(String username);

    Set<Role> loadRolesByUserId(Long userId);

    Set<Permission> loadPermissionListByUserId(Long userId);
}

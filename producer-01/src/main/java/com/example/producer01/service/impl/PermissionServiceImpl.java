package com.example.producer01.service.impl;

import com.example.producer01.mapper.PermissionMapper;
import com.example.producer01.mapper.RoleMapper;
import com.example.producer01.mapper.UserMapper;
import com.example.producer01.service.PermissionService;
import entity.Permission;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermissionByUserName(String userName) {
        User user = userMapper.loadUserByUsername(userName);
        return permissionMapper.loadPermissionListByUserId(user.getId());
    }
}

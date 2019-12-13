package com.example.producer01.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.producer01.mapper.*;
import com.example.producer01.service.UserRpcService;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
@Service(interfaceClass = UserRpcService.class)
public class UserRpcServiceImpl implements UserRpcService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleUserMapper roleUserMapper;

    @Autowired
    private PermissionRoleMapper permissionRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User loadUserByUsername(String username) {
        User user = userMapper.loadUserByUsername(username);
        return user;
    }

    @Override
    public Set<Role> loadRolesByUserId(Long userId) {
        Assert.isTrue(userId != null, "user id can not be null!");
        RoleUser ex = new RoleUser();
        ex.setUserId(userId);
        List<RoleUser> roleUsers = roleUserMapper.selectByExample(ex);
        Set<Role> roleList = new HashSet<>();
        if (!CollectionUtils.isEmpty(roleUsers)) {
            for (RoleUser roleUser : roleUsers) {
                Role role = roleMapper.selectByPrimaryKey(roleUser.getRoleId());
                if (role != null) {
                    roleList.add(role);
                }
            }
        }
        return roleList;
    }

    @Override
    public Set<Permission> loadPermissionListByUserId(Long userId) {
//        Set<Role> roleList = this.loadRolesByUserId(userId);
//        if (!CollectionUtils.isEmpty(roleList)) {
//            Set<Permission> permissions = new HashSet<>();
//            for (Role role : roleList) {
//                PermissionRole ex = new PermissionRole();
//                ex.setRoleId(role.getId());
//                List<PermissionRole> permissionRoles = permissionRoleMapper.selectByExample(ex);
//                if (!CollectionUtils.isEmpty(roleList)) {
//                    for (PermissionRole permissionRole : permissionRoles) {
//                        Permission permission = permissionMapper.selectByPrimaryKey(permissionRole.getPermissionId());
//                        if (permission != null) {
//                            permissions.add(permission);
//                        }
//                    }
//                }
//            }
//            return permissions;
//        }
//        return new HashSet<>();
        List<Permission> permissions = permissionMapper.loadPermissionListByUserId(userId);
        Set<Permission> set = new HashSet<>(permissions);
        return set;
    }
}

package com.example.producer01.service.impl;

import com.example.producer01.mapper.RoleMapper;
import com.example.producer01.service.RoleService;
import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRoleByUserName(String userName) {
        return roleMapper.findByUserName(userName);
    }
}

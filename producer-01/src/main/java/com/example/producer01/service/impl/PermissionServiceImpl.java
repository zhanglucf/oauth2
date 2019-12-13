package com.example.producer01.service.impl;

import com.example.producer01.service.PermissionService;
import entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<Permission> findPermissionByUserName(String userName) {
        return null;
    }
}

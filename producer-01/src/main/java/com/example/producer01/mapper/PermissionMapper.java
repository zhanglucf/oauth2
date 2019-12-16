package com.example.producer01.mapper;

import entity.Permission;
import util.BaseMapper;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> loadPermissionListByUserId(Long userId);

    Permission findByRoleId(Long id);
}
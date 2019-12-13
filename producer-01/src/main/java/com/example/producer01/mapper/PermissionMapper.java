package com.example.producer01.mapper;

import com.example.producer01.support.BaseMapper;
import entity.Permission;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> loadPermissionListByUserId(Long userId);
}

package com.example.producer01.service.impl;

import com.example.producer01.mapper.RoleMapper;
import com.example.producer01.mapper.RoleUserMapper;
import com.example.producer01.mapper.UserMapper;
import com.example.producer01.service.UserService;
import dto.UserDto;
import entity.Role;
import entity.RoleUser;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleUserMapper roleUserMapper;

    @Override
    public User creatUser(UserDto userDto) {
        List<String> roleDescList = userDto.getRoleDescList();
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassWord(userDto.getPassWord());
        user.setInsertTime(new Date());
        user.setUpdateTime(new Date());
        user.setInsertBy(999L);
        user.setUpdateBy(999L);
        user.setVersion(1L);
//        user.setId(3L);
        int i = userMapper.insertUseGeneratedKeys(user);
//        userMapper.insertSelective(user);
        for (String name : roleDescList) {
            Role role = roleMapper.findByName(name);
            if (role != null) {
                RoleUser roleUser = new RoleUser();
                roleUser.setSysUserId(user.getId());
                roleUser.setSysRoleId(role.getId());
                roleUser.setInsertTime(new Date());
                roleUser.setUpdateTime(new Date());
                roleUser.setInsertBy(999L);
                roleUser.setUpdateBy(999L);
                roleUser.setVersion(1L);
                roleUserMapper.insertUseGeneratedKeys(roleUser);
            }
        }
        return userDto;
    }

    @Override
    public User findByUserName(String name) {
        return userMapper.loadUserByUsername(name);
    }


    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }
}

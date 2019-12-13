package com.example.producer01.service.impl;

import com.example.producer01.service.UserService;
import dto.UserDto;
import entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User creatUser(UserDto userDto) {
        List<String> roleDescList = userDto.getRoleDescList();
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassWord(userDto.getPassWord());
        for (String s : roleDescList) {

        }
        return null;
    }

    @Override
    public User findByUserName() {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}

package com.example.producer01.mapper;

import com.example.producer01.support.BaseMapper;
import entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */

public interface UserMapper extends BaseMapper<User> {

    User loadUserByUsername(String userName);
}

package com.example.producer01.controller;

import com.example.producer01.service.PermissionService;
import com.example.producer01.service.RoleService;
import com.example.producer01.service.UserService;
import dto.UserDto;
import entity.Permission;
import entity.Role;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @PostMapping
    public User creatUser(@RequestBody UserDto userDto){
        return userService.creatUser(userDto);
    }

    @GetMapping
    public User findByUserName(@RequestParam String userName){
        return userService.findByUserName(userName);
    }

    @GetMapping("/all")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/permission")
    public List<Permission> findPermissionByUserName(@RequestParam String userName){
        return permissionService.findPermissionByUserName(userName);
    }

    @GetMapping("/role")
    public List<Role> findRoleByUserName(@RequestParam String userName){
        return roleService.findRoleByUserName(userName);
    }
}

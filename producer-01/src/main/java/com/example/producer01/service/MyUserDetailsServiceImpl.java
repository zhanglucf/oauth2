package com.example.producer01.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.oauth.user.MyUserDetails;
import entity.Permission;
import entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Reference(check = false, lazy = true, timeout = 30000)
    private UserRpcService userRpcService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRpcService.loadUserByUsername(username);
        UserDetails userDetails = userConverterToUserDetails(u);
        return userDetails;
    }

    private UserDetails userConverterToUserDetails(User u) {
        Set<Permission> permissions = userRpcService.loadPermissionListByUserId(u.getId());
        Set<String> rolesStr = new HashSet<>();
        for (Permission permission : permissions) {
            rolesStr.add(permission.getName());
        }
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setUserName(u.getUserName());
        myUserDetails.setPassWord(u.getPassWord());
        myUserDetails.setRoles(rolesStr);
        myUserDetails.setAccountNonExpired(true);
        myUserDetails.setEnabled(true);
        myUserDetails.setAccountNonLocked(true);
        myUserDetails.setCredentialsNonExpired(true);
        return myUserDetails;
    }
}

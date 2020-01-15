package com.example.oauth.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.oauth.user.MyUserDetails;
import com.example.producer01.service.UserRpcService;
import entity.Permission;
import entity.User;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
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
@Log4j
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Reference(check = false, lazy = true, timeout = 30000)
    private UserRpcService userRpcService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRpcService.loadUserByUsername(username);
        if (u == null) {
            log.error("username["+username+"] not found ");
            System.err.println("username["+username+"] not found ");
            throw new UsernameNotFoundException("username["+username+"] not found ");
        }
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

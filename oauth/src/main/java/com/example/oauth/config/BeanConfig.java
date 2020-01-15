package com.example.oauth.config;

import com.example.oauth.entity.granter.GeneralAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
@Configuration
public class BeanConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public GeneralAuthenticationProvider generalAuthenticationProvider(){
        GeneralAuthenticationProvider generalAuthenticationProvider = new GeneralAuthenticationProvider();
        generalAuthenticationProvider.setUserDetailsService(userDetailsService);
        return generalAuthenticationProvider;
    }


}

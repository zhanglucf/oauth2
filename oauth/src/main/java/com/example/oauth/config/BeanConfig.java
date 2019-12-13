package com.example.oauth.config;

import com.example.oauth.entity.granter.PasswordTokenGranter;
import com.example.oauth.entity.provider.GeneralAuthenticationProvider;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

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

package com.example.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */

@Configuration
@EnableResourceServer
public class APIResourceServerConfigurationextends extends ResourceServerConfigurerAdapter {

    @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources
                    .resourceId(AvailableResources.API_RESOURCE_ID).stateless(false);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/api/**").authenticated()
                    .antMatchers(HttpMethod.POST, "/api/**").authenticated()
                    .antMatchers(HttpMethod.PUT, "/api/**").authenticated()
                    .antMatchers(HttpMethod.DELETE, "/api/**").authenticated();
        }
}

package com.example.producer01.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加过滤器
 * @author zhenhua zhang
 * @data 2019/11/20
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        HttpRequestMDCFilter mdcFilter = new HttpRequestMDCFilter();
        filterRegistrationBean.setFilter(mdcFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);//配置过滤规则
        return filterRegistrationBean;
    }
}

package com.example.producer01.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhenhua zhang
 * @data 2019/11/20
 */
@Data
@Component
public class MyConfigBean {

    @Value("${zh.appname:BBB-TEST-APPLICATION}")
    private String appName;

}

package com.moon.config;

import com.moon.service.DemoService;
import com.moon.service.impl.spring.DemoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring框架BeanDefinition创建示例配置类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-2 23:09
 * @description
 */
@Configuration
public class BeanDefinitionTestConfiguration {

    @Bean
    public DemoService configService() {
        return new DemoServiceImpl();
    }

}

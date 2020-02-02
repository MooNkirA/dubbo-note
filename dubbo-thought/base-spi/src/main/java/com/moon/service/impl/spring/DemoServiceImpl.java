package com.moon.service.impl.spring;

import com.moon.service.DemoService;

/**
 * spring框架BeanDefinition创建示例实现
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-2 23:05
 * @description
 */
public class DemoServiceImpl implements DemoService {

    private String type = "test";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DemoServiceImpl() {
        super();
    }

    @Override
    public String sayHello(String name) {
        System.out.println("hello " + name + ", type:" + type);
        return "Hello, " + name;
    }

}

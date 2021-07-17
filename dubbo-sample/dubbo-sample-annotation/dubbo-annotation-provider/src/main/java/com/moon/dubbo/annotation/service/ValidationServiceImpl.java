package com.moon.dubbo.annotation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.entity.ValidationParameter;
import com.moon.dubbo.service.ValidationService;

/**
 * 参数校验实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-17 17:02
 * @description
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public void save(ValidationParameter parameter) {
        System.out.println("[annotation provider] ValidationService 接口实现 save 方法执行...");
        System.out.println("[ValidationParameter] is " + parameter.toString());
    }

    @Override
    public void update(ValidationParameter parameter) {
        System.out.println("[annotation provider] ValidationService 接口实现 update 方法执行...");
        System.out.println("[ValidationParameter] is " + parameter.toString());
    }

    @Override
    public void delete(int id) {
        System.out.println("[annotation provider] ValidationService 接口实现 delete 方法执行...");
        System.out.println("[id] is " + id);
    }

}

package com.moon.dubbo.annotation.service.generic;

import com.moon.dubbo.service.GenericCallService;
import org.springframework.stereotype.Component;

/**
 * 泛化调用测试实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-18 14:31
 * @description
 */
@Component
public class GenericCallServiceImpl implements GenericCallService {

    @Override
    public String getResult(String param) {
        System.out.println("[annotation provider] GenericCallService 接口实现 getResult 方法执行...");
        return "GenericCallService 接口实现方法接收参数是：" + param;
    }

}

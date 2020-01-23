package com.moon;

import com.alibaba.fastjson.JSON;
import com.moon.entity.OrderEntiry;
import com.moon.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 服务提供方
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-22 16:47
 * @description
 */
@SpringBootApplication
public class ProviderApplication {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderApplication.class);

    public static void main(String[] args) {
        // 启动容器
        ConfigurableApplicationContext context = SpringApplication.run(ProviderApplication.class, args);

        LOGGER.info("---------spring启动成功--------");

        /* 本地调用接口 */
        LOGGER.info("============ 本地接口调用 ==============");
        OrderService orderService = context.getBean(OrderService.class); // 根据接口类型获取实例
        OrderEntiry entiry = orderService.getDetail("1");
        LOGGER.info("测试orderService.getDetail调用功能，调用结果：{}", JSON.toJSONString(entiry));

    }

}

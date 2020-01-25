package com.moon;

import com.alibaba.fastjson.JSON;
import com.moon.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务消费者
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-24 18:32
 * @description
 */
@SpringBootApplication
public class ConsumerApplication {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // 启动容器
        SpringApplication.run(ConsumerApplication.class, args);

        LOGGER.info("---------spring启动成功--------");

        /* 如果不是远程调用其他服务的接口，想实现功能，只能编写与服务提供方一模一样的代码，一样的接口实现类 */
        /*LOGGER.info("============ 调用本地接口 ==============");
        OrderService orderService = context.getBean(OrderService.class);
        OrderEntiry entiry = orderService.getDetail("1");
        LOGGER.info("测试orderService.getDetail调用功能，调用结果：{}", JSON.toJSONString(entiry));*/

        /* 1. 测试远程服务调用实现 */
        // 创建rmi连接，获取远程服务实现
        InfoService infoService = (InfoService) Naming.lookup(InfoService.RMI_URL);
        Object ret = infoService.sayHello("MooNzerO");
        LOGGER.info("测试远程调用功能InfoService.sayHello()，调用结果：{}", JSON.toJSONString(ret));

        /* 2. 测试远程服务反射调用实现 */
        // 封装需要网络传输的反射调用信息
        Map<String, String> info = new HashMap<>();
        info.put("target", "com.moon.service.OrderService");
        info.put("methodName", "getDetail");
        info.put("arg", "1");
        // 调用传输数据方法
        Object result = infoService.passInfo(info);
        LOGGER.info("测试远程调用功能InfoService.passInfo()，调用结果：{}", JSON.toJSONString(result));

    }

}

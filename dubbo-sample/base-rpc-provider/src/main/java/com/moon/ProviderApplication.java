package com.moon;

import com.alibaba.fastjson.JSON;
import com.moon.entity.OrderEntiry;
import com.moon.service.InfoService;
import com.moon.service.InfoServiceImpl;
import com.moon.service.OrderService;
import com.moon.utils.InvokeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) throws Exception {
        // 启动容器
        ConfigurableApplicationContext context = SpringApplication.run(ProviderApplication.class, args);

        LOGGER.info("---------spring启动成功--------");

        /* 1. 本地调用接口 */
        LOGGER.info("============ 调用本地接口 ==============");
        OrderService orderService = context.getBean(OrderService.class); // 根据接口类型获取实例
        OrderEntiry entiry = orderService.getDetail("1");
        LOGGER.info("测试orderService.getDetail调用功能，调用结果：{}", JSON.toJSONString(entiry));

        /* 2. 通过反射调用本地接口 */
        LOGGER.info("============ 反射调用本地接口 ==============");
        Map<String, String> info = new HashMap<>();
        info.put("target", "com.moon.service.OrderService");
        info.put("methodName", "getDetail");
        info.put("arg", "1");
        Object result = InvokeUtils.call(info, context);
        LOGGER.info("测试InvokeUtils.call调用功能，调用结果：{}", JSON.toJSONString(result));

        /* 3. 通过rmi网络通信进行调用接口 */
        // initProtocol();

        /* 4. 改造，初始化RmiServer时，重写InfoService.passInfo()方法，增加获取网络传输的反射信息后，调用相应方法的逻辑 */
        initProtocol2(context);
    }

    /**
     * 初始化RmiServer，将Rmi实例绑定注册。即服务暴露
     */
    private static void initProtocol() throws RemoteException, AlreadyBoundException, MalformedURLException {
        InfoService infoService = new InfoServiceImpl();
        // 注冊通讯端口
        LocateRegistry.createRegistry(InfoService.port);
        // 注冊通讯路径
        Naming.bind(InfoService.RMI_URL, infoService);
        LOGGER.info("初始化RMI绑定");
    }

    /**
     * 获取反射信息后调用相应的方法
     *
     * @param context spring 上下文容器
     */
    private static void initProtocol2(ApplicationContext context) throws RemoteException, AlreadyBoundException, MalformedURLException {
        // 创建InfoService实现
        InfoService infoService = new InfoServiceImpl() {
            // 重写passInfo方法
            @Override
            public Object passInfo(Map<String, String> info) throws RemoteException {
                // 调用父类方法，info包含对象，方法，参数等反射需要的信息
                super.passInfo(info);

                // 使用反射进去调用
                Object result = InvokeUtils.call(info, context);
                LOGGER.info("测试调用passInfo()获取反射信息，并反射调用相应方法。调用结果：{}", JSON.toJSONString(result));
                return result;
            }
        };

        // 注冊通讯端口
        LocateRegistry.createRegistry(InfoService.port);
        // 注冊通讯路径
        Naming.bind(InfoService.RMI_URL, infoService);
        LOGGER.info("初始化RMI绑定");
    }

}

package com.moon.dubbo.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * dubbo服务提供者 - 基于xml配置方式
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-26 15:03
 * @description
 */
@SpringBootApplication
// 读取dubbo提供者配置文件
@ImportResource({"classpath:spring/dubbo-provider.xml"})
public class XmlProvider {

    public static void main(String[] args) {
        SpringApplication.run(XmlProvider.class, args);
        System.out.println("======== dubbo-xml-server 服务启动成功 ========");
    }

}

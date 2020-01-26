package com.moon.dubbo.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * dubbo服务提供者 - 基于注解方式
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-26 16:09
 * @description
 */
@SpringBootApplication
public class AnnotationProvider {

    public static void main(String[] args) {
        SpringApplication.run(AnnotationProvider.class, args);
        System.out.println("======== busi-annotation-provider服务启动成功 ========");
    }

}

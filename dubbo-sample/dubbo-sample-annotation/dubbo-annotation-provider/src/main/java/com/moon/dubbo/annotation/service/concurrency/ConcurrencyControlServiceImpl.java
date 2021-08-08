package com.moon.dubbo.annotation.service.concurrency;

import com.alibaba.dubbo.config.annotation.Method;
import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.ConcurrencyControlService;

/**
 * 并发控制实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-08-02 22:29
 * @description
 */
/* 限制当前类中每个方法在服务器端并发执行（或占用线程池线程数）不能超过 10 个 */
// @Service(executes = 10)
/* 限制当前类中的某个方法在服务器端并发执行（或占用线程池线程数）不能超过 10 个 */
// @Service(methods = {@Method(name = "concurrencyExecute", executes = 10)})
/* 限制当前类中每个方法，每客户端并发执行（或占用线程池线程数）不能超过 10 个 */
// @Service(actives = 10)
/*
 * 限制当前类中某个方法，每客户端并发执行（或占用线程池线程数）不能超过 10 个。
 *   注：@Service 与 @Reference 同时配置了 actives 属性，则以 @Reference 优化
 */
@Service(methods = {@Method(name = "concurrencyExecute", actives = 10)})
public class ConcurrencyControlServiceImpl implements ConcurrencyControlService {

    @Override
    public String concurrencyExecute() {
        System.out.println("[annotation provider] ConcurrencyControlService 接口实现 concurrencyExecute 方法执行...");
        return "annotation provider ConcurrencyControlService response data!";
    }

}

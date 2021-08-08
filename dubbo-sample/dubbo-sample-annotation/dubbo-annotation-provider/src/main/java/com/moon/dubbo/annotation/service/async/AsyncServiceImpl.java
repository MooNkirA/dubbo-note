package com.moon.dubbo.annotation.service.async;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.moon.dubbo.service.AsyncService;

import javax.servlet.AsyncContext;
import java.util.concurrent.CompletableFuture;

/**
 * 异步执行测试实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-22 21:52
 * @description
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    /**
     * 定义 CompletableFuture 签名的接口.
     * 通过 return CompletableFuture.supplyAsync() ，
     * 业务执行已从 Dubbo 线程切换到业务线程，避免了对 Dubbo 线程池的阻塞。
     *
     * @param name
     * @return
     */
    @Override
    public CompletableFuture<String> doAsync(String name) {
        System.out.println("[annotation provider] AsyncService 接口实现 doAsync 方法执行 start...");
        RpcContext savedContext = RpcContext.getContext();
        // 建议为supplyAsync提供自定义线程池，避免使用JDK公用线程池。
        // 业务执行已从 Dubbo 线程切换到业务线程，避免了对 Dubbo 线程池的阻塞。
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("receive form consumer: " + savedContext.getAttachment("consumer-key"));
            try {
                Thread.sleep(30000); // 休眠，模拟处理复杂业务
                System.out.println("[annotation provider] AsyncService 接口实现 doAsync 方法执行 end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "async response from annotation provider doAsync: " + name;
        });
    }

    /**
     * 2.7.0版本 使用AsyncContext
     * Dubbo 提供了一个类似 Serverlet 3.0 的异步接口AsyncContext，
     * 在没有 CompletableFuture 签名接口的情况下，也可以实现 Provider 端的异步执行。
     *
     * @param name
     * @return
     */
    @Override
    public String doAsyncOther(String name) {
        try {
            System.out.println("[annotation provider] AsyncService 接口实现 doAsyncOther 方法执行 start...");
            Thread.sleep(15000); // 休眠，模拟处理复杂业务
            System.out.println("[annotation provider] AsyncService 接口实现 doAsyncOther 方法执行 end...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 以下方式是 dubbo 2.7.0 版本后的全异步编程
        /*final AsyncContext asyncContext = RpcContext.startAsync();
        new Thread(() -> {
            // 如果要使用上下文，则必须要放在第一句执行
            asyncContext.signalContextSwitch();
            try {
                Thread.sleep(15000); // 休眠，模拟处理复杂业务
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 写回响应
            asyncContext.write("Hello " + name + ", async response from annotation provider.");
        }).start();*/
        return "async response from annotation provider doAsyncOther: " + name;
    }

}

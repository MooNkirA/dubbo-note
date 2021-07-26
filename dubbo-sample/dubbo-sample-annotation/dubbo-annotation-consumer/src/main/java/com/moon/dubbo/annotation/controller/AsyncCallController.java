package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Method;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.moon.dubbo.service.AsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步调用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-22 22:34
 * @description
 */
@RestController
@RequestMapping("asyncCall")
public class AsyncCallController {

    /*
     * 1. 如果服务提供者事先定义 CompletableFuture 签名的服务，则通 CompletableFuture 来实现异步调用
     * 2. 如果服务提供者定义没有返回 CompletableFuture 签名的服务，则需要使用 RpcContext 实现异步调用，
     *    需要在 @Reference 注解中的 methods 属性指定相应需要异步执行的方法名称，并且设置async属性为true
     */
    @Reference(timeout = 60000, methods = {@Method(name = "doAsyncOther", async = true)})
    private AsyncService asyncService;

    @GetMapping("completableFuture")
    public String testCompletableFuture(@RequestParam("name") String name) {
        RpcContext.getContext().setAttachment("consumer-key", "MooNZero"); // 隐式参数传递
        // 调用直接返回CompletableFuture
        CompletableFuture<String> completableFuture = asyncService.doAsync(name);
        // 增加回调
        completableFuture.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("Response: " + v);
            }
        });
        // 早于结果输出
        System.out.println("Executed before response return.");
        return "success!";
    }

    @GetMapping("rpcContext/v2.7/getCompletableFuture")
    public String testRpcContextGetCompletableFuture(@RequestParam("name") String name) {
        // 此调用会立即返回null
        asyncService.doAsyncOther(name);
        // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future，注：此方式用于2.7版本以后
        /*CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        // 为Future添加回调
        future.whenComplete((retValue, exception) -> {
            if (exception == null) {
                System.out.println(retValue);
            } else {
                exception.printStackTrace();
            }
        });*/
        // 早于结果输出
        System.out.println("Executed before response return.");
        return "success!";
    }

    @GetMapping("rpcContext/asyncCall")
    public String testRpcContextAsyncCall(@RequestParam("name") String name) throws InterruptedException {
        // 此调用会立即返回null
        System.out.println("call immediately, " + asyncService.doAsyncOther(name));

        // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future
        RpcContext.getContext().asyncCall(() -> {
            asyncService.doAsyncOther(name);
        });

        // 早于结果输出
        System.out.println("Executed before response return.");
        Thread.sleep(50000);
        return "success!";
    }

    @GetMapping("rpcContext/getFuture")
    public String testRpcContextByGetFuture(@RequestParam("name") String name) throws ExecutionException, InterruptedException {
        String result = asyncService.doAsyncOther(name);
        System.out.println("call immediately... " + result);
        // 此调用为异步调用
        // 调用方法后，此get方法会使主线程阻塞，并等待返回结果
        Object result2 = RpcContext.getContext().getFuture().get();
        // 早于结果输出
        System.out.println("Executed after getFuture().get() return." + result2);

        return "success!";
    }

}

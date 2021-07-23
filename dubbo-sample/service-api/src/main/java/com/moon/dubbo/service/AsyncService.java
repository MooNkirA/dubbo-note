package com.moon.dubbo.service;

import java.util.concurrent.CompletableFuture;

/**
 * 异步执行测试接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-22 21:47
 * @description
 */
public interface AsyncService {

    CompletableFuture<String> doAsync(String name);

    String doAsyncOther(String name);

}

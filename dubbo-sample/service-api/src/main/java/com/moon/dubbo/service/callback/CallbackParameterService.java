package com.moon.dubbo.service.callback;

/**
 * 参数回调
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-25 22:03
 * @description
 */
public interface CallbackParameterService {

    void addListener(String key, CallbackListener listener);

    String doSomething(String param);

}

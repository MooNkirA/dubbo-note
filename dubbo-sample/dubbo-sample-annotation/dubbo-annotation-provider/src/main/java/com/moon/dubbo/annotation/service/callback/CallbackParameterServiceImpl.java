package com.moon.dubbo.annotation.service.callback;

import com.alibaba.dubbo.config.annotation.Argument;
import com.alibaba.dubbo.config.annotation.Method;
import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.callback.CallbackListener;
import com.moon.dubbo.service.callback.CallbackParameterService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 参数回调实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-25 22:21
 * @description
 */
/*
 * @Method 指定方法的名称
 * @Argument 指定回调参数的信息。
 *      index属性：设置回调的参数位置
 *      callback属性：是否为回调的方法，设置true则定义回调方法
 */
@Service(methods = {@Method(name = "addListener", arguments = {@Argument(index = 1, callback = true)})})
public class CallbackParameterServiceImpl implements CallbackParameterService {

    @Override
    public void addListener(String key, CallbackListener listener) {
        // CallbackListener 是消费端去实现的回调方法
        listener.changed(doSomething(key));
    }

    /* 与回调无关的其他方法 */
    @Override
    public String doSomething(String param) {
        return param + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}

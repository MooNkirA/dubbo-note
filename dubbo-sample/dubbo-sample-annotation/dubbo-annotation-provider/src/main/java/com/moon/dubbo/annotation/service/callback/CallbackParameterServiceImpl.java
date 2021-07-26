package com.moon.dubbo.annotation.service.callback;

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

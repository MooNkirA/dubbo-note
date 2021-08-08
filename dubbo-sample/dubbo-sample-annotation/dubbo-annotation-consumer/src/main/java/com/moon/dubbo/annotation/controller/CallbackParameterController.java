package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.service.callback.CallbackListener;
import com.moon.dubbo.service.callback.CallbackParameterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数回调测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-26 21:35
 * @description
 */
@RestController
@RequestMapping("callbackParameter")
public class CallbackParameterController {

    @Reference(check = false)
    private CallbackParameterService callbackParameterService;

    @GetMapping
    public String testCallbackParameter() {
        callbackParameterService.addListener("MooN", new CallbackListener() {
            // 此方法会在服务端回调
            @Override
            public void changed(String msg) {
                System.out.println("consumer callbackParameter: " + msg);
            }
        });

        return "success!";
    }


}

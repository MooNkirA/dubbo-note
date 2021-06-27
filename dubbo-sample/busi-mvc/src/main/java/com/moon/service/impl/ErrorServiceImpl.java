package com.moon.service.impl;

import com.moon.dubbo.service.ErrorService;

public class ErrorServiceImpl implements ErrorService {

    @Override
    public String doSomeThing(String msg) {
        if (System.currentTimeMillis() % 3 == 0) {
            throw new RuntimeException("sorry, something error！");
        }
        return "success";
    }

}

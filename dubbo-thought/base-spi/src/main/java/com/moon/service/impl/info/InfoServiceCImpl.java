package com.moon.service.impl.info;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.moon.service.InfoService;

// @Adaptive // 有@Adaptive注解的实现类，优先选择做为SPI扩展接口的适配类
public class InfoServiceCImpl implements InfoService {

    @Override
    public Object sayHello(String name) {
        System.out.println(name + ",你好，调通了C实现！");
        return name + ",你好，调通了C实现！";
    }

    @Override
    public Object passInfo(String msg, URL url) {
        System.out.println("恭喜你，调通了C实现");
        return msg;
    }

}

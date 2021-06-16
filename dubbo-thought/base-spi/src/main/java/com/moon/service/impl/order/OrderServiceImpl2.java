package com.moon.service.impl.order;

import com.alibaba.dubbo.common.URL;
import com.moon.service.OrderService;
import com.moon.service.PayService;

public class OrderServiceImpl2 implements OrderService {

    /* 此接口没有@SPI注解，非Dubbo扩展点接口，其实现类实例需要在spring容器中查找并注入 */
    private PayService payService;

    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    @Override
    public String getDetail(String name, URL url) {
        payService.pay(100);
        System.out.println(name + ",OrderServiceImpl2订单处理成功！");
        return name + ",你好，OrderServiceImpl2订单处理成功！";
    }
}

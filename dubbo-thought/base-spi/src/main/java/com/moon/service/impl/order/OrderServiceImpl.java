package com.moon.service.impl.order;

import com.alibaba.dubbo.common.URL;
import com.moon.service.InfoService;
import com.moon.service.OrderService;

public class OrderServiceImpl implements OrderService {

    /* 是dubbo的扩展点，是spring的bean接口 */
    private InfoService infoService;

    public void setInfoService(InfoService infoService) {
        this.infoService = infoService;
    }

    @Override
    public String getDetail(String name, URL url) {
        infoService.passInfo(name, url);
        System.out.println(name + ",OrderServiceImpl订单处理成功！");
        return name + ",你好，OrderServiceImpl订单处理成功！";
    }
}

package com.moon.service;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.SPI;

@SPI("Zero")
public interface OrderService {
    String getDetail(String id, URL url);
}

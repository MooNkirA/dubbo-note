package com.moon.service;

import com.moon.entity.OrderEntiry;

/**
 * 订单接口
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-22 16:38
 * @description
 */
public interface OrderService {

    /**
     * 使用反射调用时，需要明确目标对象，方法，参数
     *
     * @param id
     * @return
     */
    OrderEntiry getDetail(String id);

}

package com.moon.dao;

import com.moon.dubbo.entity.OrderEntiry;

/**
 * 订单数据访问接口
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-28 11:36
 * @description
 */
public interface OrderDao {
    OrderEntiry getDetail(String id);

    OrderEntiry submit(OrderEntiry order);

    boolean cancel(OrderEntiry order);
}

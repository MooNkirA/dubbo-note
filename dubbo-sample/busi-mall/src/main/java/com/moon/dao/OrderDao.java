package com.moon.dao;

import com.moon.dubbo.entity.OrderEntiry;

public interface OrderDao {
    OrderEntiry getDetail(String id);

    OrderEntiry submit(OrderEntiry order);

    boolean cancel(OrderEntiry order);
}

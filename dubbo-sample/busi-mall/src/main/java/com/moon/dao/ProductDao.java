package com.moon.dao;

import com.moon.dubbo.entity.ProductEntiry;

public interface ProductDao {
    ProductEntiry getDetail(String id);

    ProductEntiry modify(ProductEntiry product);

    boolean status(String id, boolean upDown);
}

package com.moon.dao;

import com.moon.dubbo.entity.ProductEntiry;

/**
 * 产品数据访问接口
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-28 11:36
 * @description
 */
public interface ProductDao {
    ProductEntiry getDetail(String id);

    ProductEntiry modify(ProductEntiry product);

    boolean status(String id, boolean upDown);
}

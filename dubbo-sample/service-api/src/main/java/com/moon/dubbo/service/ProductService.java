package com.moon.dubbo.service;

import com.moon.dubbo.entity.ProductEntiry;

/**
 * 产品接口
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-22 16:40
 * @description
 */
public interface ProductService {

    ProductEntiry getDetail(String id);

    ProductEntiry modify(ProductEntiry product);

    boolean status(String id, boolean upDown);

}

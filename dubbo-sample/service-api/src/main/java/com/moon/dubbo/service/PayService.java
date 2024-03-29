package com.moon.dubbo.service;

/**
 * 支付接口
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-22 16:40
 * @description
 */
public interface PayService {

    String pay(long money);

    boolean cancelPay(long money);

}

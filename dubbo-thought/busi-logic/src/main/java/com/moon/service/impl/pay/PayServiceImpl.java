package com.moon.service.impl.pay;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.service.PayService;

/**
 * PayService接口实现
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-2 12:39
 * @description
 */
@Service
public class PayServiceImpl implements PayService {

    @Override
    public String pay(long money) {
        System.out.println("PayService.pay耗时2秒");
        return String.valueOf(money);
    }

}

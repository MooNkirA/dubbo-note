package com.moon.dubbo.service;

import java.util.Map;

/**
 * 上下文信息-隐式参数传递测试接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-21 21:27
 * @description
 */
public interface ContextAttachmentService {

    Map<String, Object> getContextData();

}

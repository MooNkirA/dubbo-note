package com.moon.dubbo.service;

import java.util.List;

/**
 * 服务分组测试接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-17 8:40
 * @description
 */
public interface GroupService {

    List<String> queryGroupData();

    String getGroupMessage();

}

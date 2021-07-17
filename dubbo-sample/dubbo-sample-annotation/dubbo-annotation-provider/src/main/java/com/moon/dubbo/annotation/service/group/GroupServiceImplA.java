package com.moon.dubbo.annotation.service.group;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.GroupService;

import java.util.Arrays;
import java.util.List;

/**
 * 分组测试实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-17 8:57
 * @description
 */
@Service(group = "groupA")
public class GroupServiceImplA implements GroupService {

    @Override
    public List<String> queryGroupData() {
        System.out.println("[annotation provider] GroupService接口[A]实现queryGroupData方法执行...");
        return Arrays.asList("group", "A");
    }

    @Override
    public String getGroupMessage() {
        System.out.println("[annotation provider] GroupService接口[A]实现getGroupMessage方法执行...");
        return "GroupA message";
    }

}

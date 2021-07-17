package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.service.GroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 服务分组测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-17 9:24
 * @description
 */
@RestController
@RequestMapping("group")
public class GroupController {

    /*
     * group属性：指定相应的服务接口的实现
     *          如果取值为"*"，则代表任意组，是随机调用不同的实现
     * 如果需要将服务分组的返回结果进行合并，只需修改 parameters 属性，{"merger", "true"}
     */
    // @Reference(group = "groupB")
    @Reference(group = "*", parameters = {"merger", "true"})
    private GroupService groupService;

    @GetMapping
    public List<String> testGroup() {
        return groupService.queryGroupData();
    }

    @GetMapping("mergerString")
    public String testMergerString() {
        return groupService.getGroupMessage();
    }

}

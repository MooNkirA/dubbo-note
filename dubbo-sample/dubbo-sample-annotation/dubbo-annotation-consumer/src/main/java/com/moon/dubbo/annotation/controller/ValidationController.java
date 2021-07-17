package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.entity.ValidationParameter;
import com.moon.dubbo.service.ValidationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数校验测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-17 17:13
 * @description
 */
@RestController
@RequestMapping("validation")
public class ValidationController {

    @Reference
    private ValidationService validationService;

    @PostMapping("save")
    public ValidationParameter testSave(@RequestBody ValidationParameter parameter) {
        validationService.save(parameter);
        return parameter;
    }

    @PostMapping("update")
    public ValidationParameter testUpdate(@RequestBody ValidationParameter parameter) {
        validationService.update(parameter);
        return parameter;
    }

    @DeleteMapping("/{id}")
    public String testUpdate(@PathVariable("id") String id) {
        validationService.delete(Integer.parseInt(id));
        return id;
    }

}

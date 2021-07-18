package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcException;
import com.moon.dubbo.entity.ValidationParameter;
import com.moon.dubbo.service.ValidationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

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
    public String testSave(@RequestBody ValidationParameter parameter) {
        try {
            validationService.save(parameter);
        } catch (ConstraintViolationException e) {
            // 可以拿到一个验证错误详细信息的集合
            Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
            for (ConstraintViolation<?> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
            return "ConstraintViolationException ERROR";
        } catch (RpcException e) { // 参数校验会抛出 RpcException
            // 里面嵌了一个ConstraintViolationException
            ConstraintViolationException ve = (ConstraintViolationException) e.getCause();
            // 可以拿到一个验证错误详细信息的集合
            Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
            System.out.println(violations);
            return "Validation ERROR";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Other ERROR";
        }
        return "success";
    }

    @PostMapping("update")
    public String testUpdate(@RequestBody ValidationParameter parameter) {
        try {
            validationService.update(parameter);
        } catch (ConstraintViolationException e) {
            // 可以拿到一个验证错误详细信息的集合
            Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
            for (ConstraintViolation<?> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
            return "ConstraintViolationException ERROR";
        } catch (RpcException e) { // 参数校验会抛出 RpcException
            // 里面嵌了一个ConstraintViolationException
            ConstraintViolationException ve = (ConstraintViolationException) e.getCause();
            // 可以拿到一个验证错误详细信息的集合
            Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
            System.out.println(violations);
            return "Validation ERROR";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Other ERROR";
        }
        return "success";
    }

    @DeleteMapping("/{id}")
    public String testUpdate(@PathVariable("id") String id) {
        try {
            validationService.delete(Integer.parseInt(id));
        } catch (ConstraintViolationException e) {
            // 可以拿到一个验证错误详细信息的集合
            Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
            for (ConstraintViolation<?> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
            return "ConstraintViolationException ERROR";
        } catch (RpcException e) { // 参数校验会抛出 RpcException
            // 里面嵌了一个ConstraintViolationException
            ConstraintViolationException ve = (ConstraintViolationException) e.getCause();
            // 可以拿到一个验证错误详细信息的集合
            Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
            System.out.println(violations);
            return "Validation ERROR";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Other ERROR";
        }
        return id;
    }

}

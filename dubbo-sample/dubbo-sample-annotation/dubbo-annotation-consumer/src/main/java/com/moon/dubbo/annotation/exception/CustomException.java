package com.moon.dubbo.annotation.exception;

/**
 * 自定义异常
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-08-01 8:37
 * @description
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

}

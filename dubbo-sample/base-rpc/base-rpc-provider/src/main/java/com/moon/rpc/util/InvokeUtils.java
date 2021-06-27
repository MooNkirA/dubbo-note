package com.moon.rpc.util;

import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 反射调用方法工具类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-23 12:19
 * @description
 */
public class InvokeUtils {

    /**
     * java反射
     *
     * @param target
     * @param methodName
     * @param argTypes
     * @param args
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object call(Object target, String methodName, Class[] argTypes, Object[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = target.getClass().getMethod(methodName, argTypes);
        // 反射调用方法
        return method.invoke(target, args);
    }

    /* 重载方法 */
    public static Object call(Map<String, String> info, ApplicationContext context) {
        String targetStr = info.get("target"); // 获取需要调用的接口全限定名
        String methodName = info.get("methodName"); // 获取调用的方法名
        String arg = info.get("arg"); // 获取调用方法的参数值

        try {
            // context.getBean(Class.forName(targetStr)) 此方法根据接口的类型，获取spring容器中对应的实现类实例
            return call(context.getBean(Class.forName(targetStr)), methodName, new Class[]{String.class}, new Object[]{arg});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

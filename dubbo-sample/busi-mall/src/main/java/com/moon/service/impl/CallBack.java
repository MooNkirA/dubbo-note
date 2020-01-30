package com.moon.service.impl;

public class CallBack {

    /**
     * 成功调用后回调方法
     *
     * @param result 返回结果值
     * @param arg    原方法调用的入参
     */
    public void onSuccess(String result, String arg) {
        System.out.println("回调测试，调用成功：" + result);
        // 处理业务逻辑...
    }

    /**
     * 调用失败后回调方法
     *
     * @param ex  出现异常返回结果值
     * @param arg 原方法调用的入参
     */
    public void onError(Throwable ex, String arg) {
        System.out.println("回调测试，调用异常，请紧急处理,异常原因：" + ex.getMessage());
        // 记录警告信息...
    }

}

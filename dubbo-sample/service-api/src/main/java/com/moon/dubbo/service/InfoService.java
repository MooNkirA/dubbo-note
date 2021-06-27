package com.moon.dubbo.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * 基础RPC测试网络传输远程请求接口，继承remote接口
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-24 17:21
 * @description
 */
public interface InfoService extends Remote {

    /* 网络传输url */
    String RMI_URL = "rmi://127.0.0.1:9080/InfoService";
    /* 端口号 */
    int port = 9080;

    Object sayHello(String name) throws RemoteException;

    /**
     * 传输信息方法
     *
     * @param info 需要调用的方法的反射信息
     * @return 远程方法执行的结果
     * @throws RemoteException
     */
    Object passInfo(Map<String, String> info) throws RemoteException;

}

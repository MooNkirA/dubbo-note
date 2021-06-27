package com.moon.rpc.service;

import com.alibaba.fastjson.JSON;
import com.moon.dubbo.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

/**
 * 网络传输远程请求接口实现
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-25 11:38
 * @description
 */
public class InfoServiceImpl extends UnicastRemoteObject implements InfoService {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoServiceImpl.class);

    public InfoServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Object sayHello(String name) throws RemoteException {
        return name + "，成功调用InfoService.sayHello()方法";
    }

    @Override
    public Object passInfo(Map<String, String> info) throws RemoteException {
        LOGGER.info("成功调通了InfoService.passInfo()方法，参数：{}", JSON.toJSONString(info));
        // 增加返回的信息
        info.put("msg", "你好，调通了！");
        return info;
    }
}

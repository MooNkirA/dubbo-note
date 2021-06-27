package com.moon.rpc.util;

import com.moon.dubbo.service.InfoService;
import com.moon.rpc.service.InfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * RmiServer 使用测试
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-25 13:38
 * @description
 */
public class RmiServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RmiServer.class);

    public static void main(String[] args) {
        try {
            InfoService infoService = new InfoServiceImpl();
            // 注冊通讯端口
            LocateRegistry.createRegistry(InfoService.port);
            // 注冊通讯路径
            Naming.bind(InfoService.RMI_URL, infoService);
            LOGGER.info("初始化RMI绑定");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}

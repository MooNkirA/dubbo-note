package com.moon.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;

/**
 * zkclient 判断节点是否存在示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-19 16:55
 * @description
 */
public class ZkclientExistsDemo {

    private ZkClient zkClient;

    @Before
    public void initConnection() {
        // 创建 ZkClient 实例即可连接到zookeeper服务端。建立连接是同步操作
        this.zkClient = new ZkClient(CONNECTION_STR, 5000);
    }

    @After
    public void closeConnection() {
        if (zkClient != null) {
            zkClient.close();
        }
    }

    /* 判断节点是否存在 */
    @Test
    public void testExists() {
        /*
         * boolean exists(final String path)
         *  参数 path：节点的路径
         */
        System.out.println(zkClient.exists("/notExists"));
        System.out.println(zkClient.exists("/exists1"));
    }

}

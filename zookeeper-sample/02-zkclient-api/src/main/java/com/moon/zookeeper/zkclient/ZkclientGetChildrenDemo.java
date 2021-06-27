package com.moon.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;

/**
 * zkclient 查看子节点示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-19 16:55
 * @description
 */
public class ZkclientGetChildrenDemo {

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

    /* 查看子节点 */
    @Test
    public void testGetChildren1() throws Exception {
        /*
         * List<String> getChildren(String path)
         *  参数 path：节点的路径
         */
        List<String> children = zkClient.getChildren("/getData/node4");
        for (String child : children) {
            System.out.println(child);
        }
    }

}

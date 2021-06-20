package com.moon.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;

/**
 * zkclient 创建节点示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-19 16:55
 * @description
 */
public class ZkclientCreateDemo {

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

    /* 创建持久节点 */
    @Test
    public void testCreate1() throws Exception {
        zkClient.createPersistent("/zkclient", "zkclient");
    }

    /* 创建临时节点 */
    @Test
    public void testCreate2() throws Exception {
        zkClient.createEphemeral("/zkclientTemp", "zkclientTemp");
    }

    /* 手动指定权限与类型 */
    @Test
    public void testCreate3() throws Exception {
        zkClient.create("/zkclient/create", "MooN", ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /* 创建有序持久节点 */
    @Test
    public void testCreate4() throws Exception {
        zkClient.createPersistentSequential("/getData/node4", "i'm a String");
    }

}

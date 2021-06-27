package com.moon.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;

/**
 * zkclient 删除节点示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-19 16:55
 * @description
 */
public class ZkclientDeleteDemo {

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

    /* 删除节点 */
    @Test
    public void testDelete1() throws Exception {
        /*
         * boolean delete(final String path, final int version)
         *  参数 path：节点的路径
         *  参数 version：版本号 -1代表版本号不作为修改条件
         * 如果删除的版本号与当前路径版本不一致，会报错：“KeeperErrorCode = BadVersion”
         */
        zkClient.delete("/delete/node2", -1);
    }

    /* 删除带有子节点的节点 */
    @Test
    public void testDelete2() throws Exception {
        /*
         * boolean deleteRecursive(String path)
         *  参数 path：节点的路径
         * 用于递归删除有多个子节点的节点
         */
        zkClient.deleteRecursive("/delete/node3");
    }

}

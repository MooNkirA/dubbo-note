package com.moon.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;

/**
 * zkclient 更新节点示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-19 16:55
 * @description
 */
public class ZkclientUpdateDemo {

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

    /* 更新持久节点 */
    @Test
    public void testWriteData1() throws Exception {
        /*
         * void writeData(final String path, Object datat, final int expectedVersion)
         *  参数 path：节点的路径
         *  参数 datat：节点修改的数据
         *  参数 expectedVersion：版本号 -1代表版本号不作为修改条件
         * 如果修改的版本号与当前路径版本不一致，会报错：“KeeperErrorCode = BadVersion”
         */
        zkClient.writeData("/set/node2", "MooN...", -1);
    }

}

package com.moon.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;

/**
 * zkclient 连接zookeeper服务端示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-19 16:55
 * @description
 */
public class ZkclientConnectionDemo {

    /**
     * 测试 zkclient 连接
     */
    @Test
    public void testZkclientConnection() throws Exception {
        // 创建 ZkClient 实例即可连接到zookeeper服务端。
        // 需要注意，与原生ZooKeeper创建异步的过程不一样，ZkClient建立连接是同步的
        ZkClient zkClient = new ZkClient(CONNECTION_STR, 5000);

        // 创建文件输出流
        OutputStream ops = new FileOutputStream("E:\\00-Downloads\\zkFolders.txt");
        // 读取zookeeper服务端的文件夹
        zkClient.showFolders(ops);

        // 关闭连接
        zkClient.close();
    }

}

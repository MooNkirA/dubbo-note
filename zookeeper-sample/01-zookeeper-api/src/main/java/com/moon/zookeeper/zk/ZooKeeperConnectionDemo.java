package com.moon.zookeeper.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;

/**
 * Zookeeper 连接服务端 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-18 17:17
 * @description
 */
public class ZooKeeperConnectionDemo {

    /**
     * 测试 ZooKeeper 连接
     */
    @Test
    public void testZooKeeperConnection() {
        try {
            // 创建计数器对象
            CountDownLatch countDownLatch = new CountDownLatch(1);
            /*
             * 创建 ZooKeeper 实例即可连接到zookeeper服务端。建立连接本身是一个异步过程
             * ZooKeeper(String connectString, int sessionTimeout, Watcher watcher)
             *  参数 connectString: 服务器的ip和端口
             *  参数 sessionTimeout: 客户端与服务器之间的会话超时时间，单位是：毫秒
             *  参数 watcher: 监视器对象
             */
            ZooKeeper zooKeeper = new ZooKeeper(CONNECTION_STR, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("当前状态：" + event.getState());
                    // 判断当前状态是否连接
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        System.out.println("连接创建成功!");
                        // 连接成功后，释放所有等待的线程
                        countDownLatch.countDown();
                    }
                }
            });

            // 主线程阻塞等待连接对象的创建成功
            countDownLatch.await();
            // 会话编号
            System.out.println("会话编号: " + zooKeeper.getSessionId());
            // 关闭连接
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package com.moon.zookeeper.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;

/**
 * 客服端与服务器的连接状态 Watcher
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-22 19:10
 * @description
 */
public class ZKConnectionWatcher implements Watcher {

    // 计数器对象
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    // 连接对象
    private ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent event) {
        try {
            // 事件类型
            if (event.getType() == Event.EventType.None) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("连接创建成功!");
                    countDownLatch.countDown();
                } else if (event.getState() == Event.KeeperState.Disconnected) {
                    System.out.println("断开连接！");
                } else if (event.getState() == Event.KeeperState.Expired) {
                    System.out.println("会话超时!");
                    zooKeeper = new ZooKeeper(CONNECTION_STR, 5000, new ZKConnectionWatcher());
                } else if (event.getState() == Event.KeeperState.AuthFailed) {
                    System.out.println("认证失败！");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testWatcher() {
        try {
            zooKeeper = new ZooKeeper(CONNECTION_STR, 5000, new ZKConnectionWatcher());
            // 阻塞线程等待连接的创建
            countDownLatch.await();
            // 会话id
            System.out.println(zooKeeper.getSessionId());
            // 添加授权用户
            // zooKeeper.addAuthInfo("digest1", "MooN:123456".getBytes());
            byte[] bs = zooKeeper.getData("/MooN/node1", false, null);
            System.out.println(new String(bs));
            Thread.sleep(50000);
            zooKeeper.close();
            System.out.println("结束");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

package com.moon.zookeeper.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * 自定义 Watcher，实现监听zookeeper节点
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-18 22:40
 * @description
 */
public class CustomWatcher implements Watcher {

    private CountDownLatch countDownLatch;

    public CustomWatcher(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("当前状态WatchedEvent.state: " + event.getState());
        // 判断是否连接状态
        if (Event.KeeperState.SyncConnected == event.getState()) {
            System.out.println("success syncConnected. 创建连接成功");
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                System.out.println("Zookeeper session established");
            } else if (Event.EventType.NodeCreated == event.getType()) {
                // 节点创建
                System.out.println("success create znode");
            } else if (Event.EventType.NodeDataChanged == event.getType()) {
                // 节点数据更新
                System.out.println("success change znode: " + event.getPath());
            } else if (Event.EventType.NodeDeleted == event.getType()) {
                // 删除节点
                System.out.println("success delete znode");
            } else if (Event.EventType.NodeChildrenChanged == event.getType()) {
                // 子节点数据更新
                System.out.println("NodeChildrenChanged");
            }
            // 连接成功后，释放所有等待的线程
            countDownLatch.countDown();
        }
    }

}

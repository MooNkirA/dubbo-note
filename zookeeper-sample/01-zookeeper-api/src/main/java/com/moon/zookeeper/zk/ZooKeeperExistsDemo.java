package com.moon.zookeeper.zk;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;

/**
 * ZooKeeper 检查节点是否存在 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-19 12:23
 * @description
 */
public class ZooKeeperExistsDemo {

    private ZooKeeper zooKeeper;

    @Before
    public void initZkConnection() throws Exception {
        // 创建计数器对象
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 创建 ZooKeeper 实例即可连接到zookeeper服务端。建立连接本身是一个异步过程
        this.zooKeeper = new ZooKeeper(CONNECTION_STR, 5000, event -> {
            System.out.println("当前状态：" + event.getState());
            // 判断当前状态是否连接
            if (event.getState() == Event.KeeperState.SyncConnected) {
                System.out.println("连接创建成功!");
                // 连接成功后，释放所有等待的线程
                countDownLatch.countDown();
            }
        });
        // 主线程阻塞等待连接对象的创建成功
        countDownLatch.await();
    }

    @After
    public void closeZkConnection() throws Exception {
        if (zooKeeper != null) {
            zooKeeper.close();
        }
    }

    @Test
    public void exists1() throws Exception {
        /*
         * 同步方式，检查节点
         * Stat exists(String path, boolean watch)
         *   参数 path: 节点的路径
         *   参数 watch: 是否使用连接对象中注册的监视器
         */
        Stat stat = zooKeeper.exists("/exists1", false);
        // stat 为null，代表不存在
        System.out.println(stat.getVersion());
    }

    @Test
    public void exists2() throws Exception {
        /*
         * 异步方式，检查节点
         * void exists(String path, boolean watch, StatCallback cb, Object ctx)
         *   参数 path: 节点的路径
         *   参数 watch: 是否使用连接对象中注册的监视器
         *   参数 cb: 异步回调接口
         *   参数 ctx: 传递上下文参数
         */
        zooKeeper.exists("/exists2", false, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, Stat stat) {
                // 0 判断成功
                System.out.println(rc);
                // 路径
                System.out.println(path);
                // 上下文参数
                System.out.println(ctx);
                // null 节点不存在
                System.out.println(stat.getVersion());
            }
        }, "I am Context");
        Thread.sleep(5000);
        System.out.println("结束");
    }

}

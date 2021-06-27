package com.moon.zookeeper.zk;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;

/**
 * ZooKeeper 删除节点 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-19 12:14
 * @description
 */
public class ZooKeeperDeleteDemo {

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
    public void delete1() throws Exception {
        /*
         * 同步方式，删除节点
         * void delete(final String path, int version)
         *   参数 path: 删除节点的节点路径
         *   参数 version: 数据版本信息 -1代表删除节点时不考虑版本信息
         * 如果设置版本号不正确，会报错 “KeeperErrorCode = BadVersion for xxx”
         */
        zooKeeper.delete("/delete/node1", -1);
    }

    @Test
    public void delete2() throws Exception {
        /*
         * 异步方式，删除节点
         * void delete(final String path, int version, VoidCallback cb, Object ctx)
         *   参数 path: 节点的路径
         *   参数 version: znode的当前版本号。`-1`代表删除节点时不考虑版本信息
         *   参数 cb: 异步回调接口
         *   参数 ctx: 传递上下文参数
         */
        zooKeeper.delete("/delete/node2", -1, new AsyncCallback.VoidCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx) {
                // 0代表删除成功
                System.out.println(rc);
                // 节点的路径
                System.out.println(path);
                // 上下文参数对象
                System.out.println(ctx);
            }
        }, "I am Context");
        Thread.sleep(10000);
        System.out.println("结束");
    }

}

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
 * ZooKeeper 更新节点 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-19 9:56
 * @description
 */
public class ZooKeeperUpdateDemo {

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
    public void update1() throws Exception {
        /*
         * 同步方式，更新节点
         * Stat setData(final String path, byte data[], int version)
         *   参数 path: 节点的路径
         *   参数 data[]: 节点修改的数据
         *   参数 version: 版本号 -1代表版本号不作为修改条件
         * 如果设置版本号不正确，会报错 “KeeperErrorCode = BadVersion for xxx”
         */
        Stat stat = zooKeeper.setData("/set/node1", "node13".getBytes(), -1);
        // 节点的版本号
        System.out.println(stat.getVersion());
        // 节点的创建时间
        System.out.println(stat.getCtime());
    }

    @Test
    public void update2() throws Exception {
        /*
         * 异步方式修改节点
         * void setData(final String path, byte data[], int version, StatCallback cb, Object ctx)
         *   参数 path: 节点的路径
         *   参数 data[]: 节点修改的数据
         *   参数 version: 版本号 -1代表版本号不作为修改条件
         *   参数 cb: 异步回调接口
         *   参数 ctx: 传递上下文参数
         */
        zooKeeper.setData("/set/node2", "node21".getBytes(), -1, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, Stat stat) {
                // 0 代表修改成功
                System.out.println(rc);
                // 修改节点的路径
                System.out.println(path);
                // 上线文的参数对象
                System.out.println(ctx);
                // 的属性信息
                System.out.println(stat.getVersion());
            }
        }, "I am Context");
        Thread.sleep(50000);
        System.out.println("结束");
    }
}

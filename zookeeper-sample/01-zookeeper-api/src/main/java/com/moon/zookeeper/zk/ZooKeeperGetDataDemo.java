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
 * ZooKeeper 查看节点 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-19 12:23
 * @description
 */
public class ZooKeeperGetDataDemo {

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
    public void getData1() throws Exception {
        /*
         * 同步方式，查看节点
         * byte[] getData(String path, boolean watch, Stat stat)
         *   参数 path: 节点的路径
         *   参数 watch: 是否使用连接对象中注册的监视器
         *   参数 stat: 读取节点属性的对象
         */
        Stat stat = new Stat();
        byte[] bys = zooKeeper.getData("/getData/node1", false, stat);
        // 打印数据
        System.out.println(new String(bys));
        // 版本信息
        System.out.println(stat.getVersion());
    }

    @Test
    public void getData2() throws Exception {
        /*
         * 异步方式，查看节点
         * void getData(String path, boolean watch, DataCallback cb, Object ctx)
         *   参数 path: 节点的路径
         *   参数 watch: 是否使用连接对象中注册的监视器
         *   参数 cb: 异步回调接口
         *   参数 ctx: 传递上下文参数
         */
        zooKeeper.getData("/getData/node1", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                // 0代表读取成功
                System.out.println(rc);
                // 节点的路径
                System.out.println(path);
                // 上下文参数对象
                System.out.println(ctx);
                // 数据
                System.out.println(new String(data));
                // 属性对象
                System.out.println(stat.getVersion());
            }
        }, "I am Context");
        Thread.sleep(10000);
        System.out.println("结束");
    }

}

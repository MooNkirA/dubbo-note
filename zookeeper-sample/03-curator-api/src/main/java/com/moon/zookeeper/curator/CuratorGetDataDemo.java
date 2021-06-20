package com.moon.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.moon.zookeeper.constant.Constants.*;

/**
 * curator 客户端查看节点 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-20 14:07
 * @description
 */
public class CuratorGetDataDemo {

    private CuratorFramework client;

    @Before
    public void initConnection() throws Exception {
        // session重连策略
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        // 创建连接对象
        client = CuratorFrameworkFactory.builder()
                .connectString(CONNECTION_STR) // 服务端IP地址与端口号
                .sessionTimeoutMs(5000) // 会话超时时间
                .retryPolicy(retryPolicy) // 设置重连机制
                .namespace(CURATOR_NAMESPACE) // 命名空间
                .build(); // 构建连接对象
        // 开启连接
        client.start();
    }

    @After
    public void closeConnection() throws Exception {
        if (client != null) {
            // 关闭连接
            client.close();
        }
    }

    /* 读取节点数据 */
    @Test
    public void getData1() throws Exception {
        byte[] bys = client.getData()
                /*
                 * byte[] forPath(String path)
                 * 查看指定的节点，具体实现是 GetDataBuilderImpl
                 *  参数 path：节点的路径
                 */
                .forPath("/node1");
        System.out.println(new String(bys));
    }

    /* 读取数据时，读取节点的属性 */
    @Test
    public void getData2() throws Exception {
        // 创建节点属性对象
        Stat stat = new Stat();
        byte[] bys = client.getData()
                // 读取属性
                .storingStatIn(stat)
                .forPath("/node1");
        System.out.println("节点的数据" + new String(bys));
        System.out.println("节点数据的更改次数: " + stat.getVersion());
        System.out.println("数据节点最后一次更新时的事务 ID : " + stat.getMzxid());
    }

    /* 异步方式读取节点的数据 */
    @Test
    public void getData3() throws Exception {
        client.getData()
                // 异步回调接口
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        // 节点的路径
                        System.out.println(curatorEvent.getPath());
                        // 事件的类型
                        System.out.println(curatorEvent.getType());
                        // 节点的数据
                        System.out.println(new String(curatorEvent.getData()));
                    }
                })
                .forPath("/node1");
        Thread.sleep(5000);
        System.out.println("读取节点的数据结束");
    }

}

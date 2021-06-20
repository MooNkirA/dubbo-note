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
 * curator 客户端更新节点 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-20 14:07
 * @description
 */
public class CuratorSetDataDemo {

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

    /* 更新节点 */
    @Test
    public void setData1() throws Exception {
        Stat stat = client.setData()
                /*
                 * Stat forPath(String path, byte[] data)
                 * 指定更新的节点，具体实现是 SetDataBuilderImpl
                 *  参数 path：节点的路径
                 *  参数 data：节点的数据
                 */
                .forPath("/node1", "abc".getBytes());
        System.out.println("节点数据的更改次数: " + stat.getVersion());
        System.out.println("数据节点最后一次更新时的事务 ID : " + stat.getMzxid());
    }

    /* 指定版本号更新节点 */
    @Test
    public void setData2() throws Exception {
        Stat stat = client.setData()
                // 指定版本号，如果版本与修改的节点版本号不一致，会报错“KeeperErrorCode = BadVersion”
                // -1代表版本号不作为修改条件
                .withVersion(-1)
                .forPath("/node1", "L&N".getBytes());
        System.out.println("节点数据的更改次数: " + stat.getVersion());
        System.out.println("数据节点最后一次更新时的事务 ID : " + stat.getMzxid());
    }

    /* 异步方式修改节点数据 */
    @Test
    public void setData3() throws Exception {
        client.setData()
                .withVersion(-1)
                // 异步回调接口
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        // 节点的路径
                        System.out.println(curatorEvent.getPath());
                        // 事件的类型
                        System.out.println(curatorEvent.getType());
                    }
                })
                .forPath("/node1", "L?".getBytes());
        Thread.sleep(5000);
        System.out.println("更新节点结束");
    }

}

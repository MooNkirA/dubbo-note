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

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;
import static com.moon.zookeeper.constant.Constants.CURATOR_NAMESPACE;

/**
 * curator 客户端查看节点是否存在 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-20 14:07
 * @description
 */
public class CuratorCheckExistsDemo {

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

    /* 判断节点是否存在 */
    @Test
    public void checkExists1() throws Exception {
        Stat stat = client.checkExists()
                /*
                 * Stat forPath(String path)
                 * 指定更新的节点，具体实现是 ExistsBuilderImpl
                 *  参数 path：节点的路径
                 */
                .forPath("/node211");
        if (stat == null) {
            System.out.println("节点不存在");
        } else {
            System.out.println("节点数据的更改次数: " + stat.getVersion());
            System.out.println("数据节点最后一次更新时的事务 ID : " + stat.getMzxid());
        }
    }

    /* 异步方式判断节点是否存在 */
    @Test
    public void checkExists2() throws Exception {
        client.checkExists()
                // 异步回调接口
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        // 节点路径
                        System.out.println(curatorEvent.getPath());
                        // 事件类型
                        System.out.println(curatorEvent.getType());
                        // 结果码resultCode，存在返回值为0，不存在返回值为-101
                        System.out.println("resultCode: " + curatorEvent.getResultCode());
                        // 获取节点的属性
                        Stat stat = curatorEvent.getStat();
                        if (stat == null) {
                            System.out.println("节点不存在");
                        } else {
                            System.out.println("节点数据的更改次数: " + stat.getVersion());
                            System.out.println("数据节点最后一次更新时的事务 ID : " + stat.getMzxid());
                        }
                    }
                })
                .forPath("/node211");
        Thread.sleep(5000);
        System.out.println("异步方式判断节点结束");
    }

}

package com.moon.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.moon.zookeeper.constant.Constants.*;

/**
 * curator 客户端查看子节点 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-20 14:07
 * @description
 */
public class CuratorGetChildrenDemo {

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

    /* 读取子节点数据 */
    @Test
    public void getChildren1() throws Exception {
        List<String> list = client.getChildren()
                /*
                 * List<String> forPath(String path)
                 * 查看指定节点的子节点列表，具体实现是 GetChildrenBuilderImpl
                 *  参数 path：节点的路径
                 *  参数 data：节点的数据
                 */
                .forPath("/node5");
        for (String str : list) {
            System.out.println(str);
        }
    }

    /* 异步方式读取子节点数据 */
    @Test
    public void getChildren2() throws Exception {
        client.getChildren()
                // 异步回调接口
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        // 节点路径
                        System.out.println(curatorEvent.getPath());
                        // 事件类型
                        System.out.println(curatorEvent.getType());
                        // 读取子节点数据
                        List<String> list = curatorEvent.getChildren();
                        for (String str : list) {
                            System.out.println(str);
                        }
                    }
                })
                .forPath("/node5");
        Thread.sleep(5000);
        System.out.println("异步读取子节点列表结束");
    }

}

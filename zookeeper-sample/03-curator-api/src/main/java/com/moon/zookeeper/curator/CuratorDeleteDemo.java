package com.moon.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.moon.zookeeper.constant.Constants.*;

/**
 * curator 客户端删除节点 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-20 14:07
 * @description
 */
public class CuratorDeleteDemo {

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

    /* 删除节点 */
    @Test
    public void delete1() throws Exception {
        client.delete()
                /*
                 * Void forPath(String path)
                 * 指定删除节点，具体实现是 DeleteBuilderImpl
                 *  参数 path：节点的路径
                 */
                .forPath("/node1");
        System.out.println("删除操作结束");
    }

    /* 指定版本号删除节点 */
    @Test
    public void delete2() throws Exception {
        client.delete()
                // 指定版本号，如果版本与修改的节点版本号不一致，会报错“KeeperErrorCode = BadVersion”
                // -1代表版本号不作为修改条件
                .withVersion(-1)
                .forPath("/node2");
        System.out.println("删除操作结束");
    }

    /* 删除节点，包含其子节点 */
    @Test
    public void delete3() throws Exception {
        client.delete()
                // 设置删除其子节点
                .deletingChildrenIfNeeded()
                .withVersion(-1)
                .forPath("/node3");
        System.out.println("删除操作结束");
    }

    /* 异步方式删除节点 */
    @Test
    public void delete4() throws Exception {
        client.delete()
                .deletingChildrenIfNeeded()
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
                .forPath("/node4");
        Thread.sleep(5000);
        System.out.println("删除操作结束");
    }

}

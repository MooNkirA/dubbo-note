package com.moon.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.moon.zookeeper.constant.Constants.*;

/**
 * curator 客户端新增节点 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-20 14:07
 * @description
 */
public class CuratorCreateDemo {

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

    /* 新增节点 */
    @Test
    public void create1() throws Exception {
        String result = client.create()
                // 节点的类型
                .withMode(CreateMode.PERSISTENT)
                // 节点的权限列表 world:anyone:cdrwa
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                /*
                 * String forPath(final String givenPath, byte[] data)
                 * 指定新增节点，具体实现是 CreateBuilderImpl
                 *  参数 path：节点的路径
                 *  参数 data：节点的数据
                 */
                .forPath("/node1", "MooN".getBytes());
        System.out.println("result is " + result);
    }

    /* 新增节点，设置自定义权限 */
    @Test
    public void create2() throws Exception {
        // 权限列表
        List<ACL> list = new ArrayList<ACL>();
        // 授权模式和授权对象
        Id id = new Id("ip", "127.0.0.1");
        list.add(new ACL(ZooDefs.Perms.ALL, id));
        String result = client.create()
                .withMode(CreateMode.PERSISTENT)
                // 节点的自定义权限列表
                .withACL(list)
                .forPath("/node2", "Zero".getBytes());
        System.out.println("result is " + result);
    }

    /* 递归创建节点树 */
    @Test
    public void create3() throws Exception {
        String result = client.create()
                // 递归节点的创建，如果父节点不存在，将自动创建
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/node3/node41/node412/node413", "kira".getBytes());
        System.out.println("result is " + result);
    }

    /* 异步方式创建节点 */
    @Test
    public void create4() throws Exception {
        String result = client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                // 异步回调接口
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        // 节点的路径
                        System.out.println(curatorEvent.getPath());
                        // 事件的类型
                        System.out.println(curatorEvent.getType());
                    }
                })
                .forPath("/node4", "haha".getBytes());
        Thread.sleep(5000);
        System.out.println("result is " + result);
    }

}

package com.moon.zookeeper.transaction;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;
import static com.moon.zookeeper.constant.Constants.CURATOR_NAMESPACE;

/**
 * Curator 客户端
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-20 20:42
 * @description
 */
public class CuratorTransactionDemo {

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

    /* Curator 控制事务，示例会创建两个节点，并在第二个节点中故意制造语法错误，观察是否支持事务 */
    @Test
    public void testTransaction() throws Exception {
        // 通过 inTransaction() 方法创建 CuratorTransaction 事务操作对象，开启事务
        client.inTransaction()
                .create()
                .forPath("/node6", "MooN".getBytes())
                .and()
                .create()
                .forPath("node7", "Zero".getBytes())
                .and()
                // 提交事务
                .commit();
    }

}

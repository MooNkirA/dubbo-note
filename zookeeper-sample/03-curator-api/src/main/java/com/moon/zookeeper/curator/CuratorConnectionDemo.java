package com.moon.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.retry.RetryUntilElapsed;
import org.junit.Test;

import static com.moon.zookeeper.constant.Constants.*;

/**
 * curator 客户端连接 zookeeper 服务端 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-20 14:07
 * @description
 */
public class CuratorConnectionDemo {

    @Test
    public void testCuratorConnection() {
        // 创建连接对象，可使用建造者链接编程方式
        // 与原生ZooKeeper连接对象异步创建不一样，该连接对象的创建是同步
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(CONNECTION_STR) // 服务端IP地址与端口号
                .sessionTimeoutMs(5000) // 会话超时时间
                // 设置重连机制。
                // .retryPolicy(new RetryOneTime(3000)) // session重连策略：3秒后重连一次，只重连1次
                // .retryPolicy(new RetryNTimes(3, 3000)) // session重连策略：每3秒重连一次，重连3次
                // .retryPolicy(new RetryUntilElapsed(10000, 3000)) // session重连策略：每3秒重连一次，总等待时间超过10秒后停止重连
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)) // session重连策略：baseSleepTimeMs * Math.max(1, random.nextInt(1 << (retryCount + 1)))
                .namespace(CURATOR_NAMESPACE) // 命名空间
                .build(); // 构建连接对象
        // 打开连接
        client.start();
        System.out.println(client.getState()); // 客户端状态
        System.out.println(client.isStarted()); // 客户端是否连接
        // 关闭连接
        client.close();
    }

}

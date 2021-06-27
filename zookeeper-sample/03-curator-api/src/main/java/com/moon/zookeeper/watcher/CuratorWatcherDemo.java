package com.moon.zookeeper.watcher;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;
import static com.moon.zookeeper.constant.Constants.CURATOR_NAMESPACE;

/**
 * curator 客户端 Watcher 实现 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-20 16:06
 * @description
 */
public class CuratorWatcherDemo {

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

    @Test
    public void testNodeCache() throws Exception {
        /*
         * 创建NodeCache对象，监视某个节点的数据变化，构造函数如下：
         * NodeCache(CuratorFramework client, String path)
         *   参数 client: 连接对象
         *   参数 path: 连接对象
         * 需要注意的是：如果CuratorFramework设置了namespace,则监听的节点是"namespace+path"
         */
        final NodeCache nodeCache = new NodeCache(client, "/node1");
        // 启动监视器对象
        nodeCache.start();
        // 注册监听器
        nodeCache.getListenable()
                .addListener(new NodeCacheListener() {
                    // 节点每次变化时都回调此方法
                    public void nodeChanged() throws Exception {
                        // 从 NodeCache 对象可获取到节点修改后信息
                        System.out.println(nodeCache.getCurrentData().getPath());
                        System.out.println(new String(nodeCache.getCurrentData().getData()));
                    }
                });

        // 对被监听的节点进行多次修改
        client.setData()
                .forPath("/node1", "我改一下中文看看".getBytes(StandardCharsets.UTF_8));
        client.setData()
                .forPath("/node1", "我改第二次".getBytes(StandardCharsets.UTF_8));

        Thread.sleep(10000); // 休眠主线程，等待监视器的结果输出
        System.out.println("示例结束");
        // 关闭监视器对象
        nodeCache.close();
    }

    @Test
    public void testPathChildrenCache() throws Exception {
        /*
         * 创建 PathChildrenCache 对象，监视某个节点的所有子节点的数据变化，构造函数如下：
         * PathChildrenCache(CuratorFramework client, String path, boolean cacheData)
         *   参数 client: 连接对象
         *   参数 path: 监视的节点路径
         *   参数 cacheData: 事件中是否可以获取节点的数据
         */
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/node5", true);
        // 启动监听
        pathChildrenCache.start();
        // 注册监听器
        pathChildrenCache.getListenable()
                .addListener(new PathChildrenCacheListener() {
                    // 当某个子节点数据变化时，回调此方法（注意是所有子节点都会回调）
                    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                        // 节点的事件类型
                        System.out.println(pathChildrenCacheEvent.getType());
                        // 节点的路径
                        System.out.println(pathChildrenCacheEvent.getData().getPath());
                        // 节点的数据
                        System.out.println(new String(pathChildrenCacheEvent.getData().getData()));
                    }
                });

        // 对被监听的节点进行多次修改
        client.setData()
                .forPath("/node5/node51", "MooNkirA".getBytes());

        Thread.sleep(10000); // 休眠主线程，等待监视器的结果输出
        System.out.println("示例结束");
        // 关闭监听
        pathChildrenCache.close();
    }

}

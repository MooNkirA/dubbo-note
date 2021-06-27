package com.moon.zookeeper.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;
import static com.moon.zookeeper.constant.Constants.CURATOR_NAMESPACE;

/**
 * ZkLock 接口抽象实现，定义核心的流程
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-22 22:28
 * @description
 */
public abstract class AbstractZkLock implements ZkLock {

    // Zookeeper 锁的节点名称
    public final static String LOCK_PATH = "/lock";

    // 创建连接对象
    protected CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(CONNECTION_STR) // 服务端IP地址与端口号
            .sessionTimeoutMs(5000) // 会话超时时间
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)) // 设置重连机制
            .namespace(CURATOR_NAMESPACE) // 命名空间
            .build(); // 构建连接对象;

    /**
     * 上锁。主要的处理流程：
     * 获取锁成功：继续执行业务逻辑，业务逻辑执行成功后，释放锁（即调用 unlock 方法）
     * 获取锁失败：
     * 1. 进行等待，等其他客户端释放锁
     * 2. 递归调用获取锁方法（即 lock），重复上述流程
     */
    @Override
    public void lock() {
        // 获取锁
        if (tryLock()) {
            // 成功获取
            System.out.println(Thread.currentThread().getName() + " --> 获取锁成功！");
        } else {
            // 获取失败，
            waitForLock();
            // 等待完后，递归调用lock方法，重复上述流程
            lock();
        }
    }

    /**
     * 释放锁，因为是创建临时节点，直接将会话关闭即可
     */
    @Override
    public void unlock() {
        client.close();
    }

    /**
     * 获取锁，钩子方法，由不同子类实现
     *
     * @return 成功返回true，否则返回false
     */
    protected abstract boolean tryLock();

    /**
     * 等待锁，钩子方法，由不同子类实现
     */
    protected abstract void waitForLock();

}

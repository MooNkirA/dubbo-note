package com.moon.zookeeper.lock.impl;

import com.moon.zookeeper.lock.AbstractZkLock;
import org.apache.zookeeper.CreateMode;

/**
 * 基于 Zookeeper 独占锁实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-22 23:06
 * @description
 */
public class ExclusiveLockImpl extends AbstractZkLock {

    /**
     * 获取锁，钩子方法，由不同子类实现
     *
     * @return 成功返回true，否则返回false
     */
    @Override
    protected boolean tryLock() {
        try {
            client.create()
                    .withMode(CreateMode.EPHEMERAL) // 创建临时节点
                    .forPath(LOCK_PATH); // 指定节点名称
            return true; // 成功返回true
        } catch (Exception ex) {
            ex.printStackTrace();
            return false; // 出现异常，无法新增
        }
    }

    /**
     * 等待锁，钩子方法，由不同子类实现
     */
    @Override
    protected void waitForLock() {

    }

}

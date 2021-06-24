package com.moon.zookeeper.lock;

/**
 * 基于 Zookeeper 的锁接口定义
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-22 22:19
 * @description
 */
public interface ZkLock {

    /**
     * 获取锁
     */
    void lock();

    /**
     * 释放锁
     */
    void unlock();

}

package com.moon.zookeeper.constant;

/**
 * 常量
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-18 22:02
 * @description
 */
public final class Constants {

    private Constants() {
    }

    // zookeeper 服务端连接字符串
    public static final String CONNECTION_STR = "127.0.0.1:2181";
    // Curator 创建连接的命名空间
    public static final String CURATOR_NAMESPACE = "MooN";

}

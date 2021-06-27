package com.moon.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.moon.zookeeper.constant.Constants.CONNECTION_STR;

/**
 * zkclient 查看节点示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-19 16:55
 * @description
 */
public class ZkclientReadDataDemo {

    private ZkClient zkClient;

    @Before
    public void initConnection() {
        // 创建 ZkClient 实例即可连接到zookeeper服务端。建立连接是同步操作
        // ZkSerializer 用于定义节点存储的数据序列化
        this.zkClient = new ZkClient(CONNECTION_STR, 500000, 5000, new ZkSerializer() {
            @Override
            public byte[] serialize(Object data) throws ZkMarshallingError {
                return new byte[0];
            }

            @Override
            public Object deserialize(byte[] bytes) throws ZkMarshallingError {
                return new String(bytes);
            }
        });
    }

    @After
    public void closeConnection() {
        if (zkClient != null) {
            zkClient.close();
        }
    }

    /* 查看节点 */
    @Test
    public void testReadData1() throws Exception {
        /*
         * <T extends Object> T readData(String path)
         *  参数 path：节点的路径
         * 这里默认会调用另一个重载的方法，returnNullIfPathNotExists 参数值为 false
         * 即如果节点不存在，则会直接抛出异常
         */
        Object data = zkClient.readData("/getData/node1");
        System.out.println(data.toString());
    }

    /* 查看节点，如果节点不存在，则返回null，并且不会抛出异常 */
    @Test
    public void testReadData2() throws Exception {
        /*
         * <T extends Object> T readData(String path, boolean returnNullIfPathNotExists)
         *  参数 path：节点的路径
         *  参数 returnNullIfPathNotExists：如果节点不存在，是否返回null，而不会抛出异常
         */
        Object data = zkClient.readData("/getData/notExists", true);
        System.out.println(data);
    }

}

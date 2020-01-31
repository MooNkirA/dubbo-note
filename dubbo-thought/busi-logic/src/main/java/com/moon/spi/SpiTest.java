package com.moon.spi;

import com.moon.service.InfoService;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * SPI 示例测试
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-31 11:57
 * @description
 */
public class SpiTest {

    /**
     * 测试Java SPI机制
     */
    @Test
    public void testBaseSpi() {
        //服务加载器，加载实现类
        ServiceLoader<InfoService> serviceLoader = ServiceLoader.load(InfoService.class);

        // ServiceLoader是实现了Iterable的迭代器，直接遍历实现类
        for (InfoService service : serviceLoader) {
            Object result = service.sayHello("Moon"); // 依次调用文件中配置的所有实现类
        }
    }

}

package com.moon.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
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

    /**
     * dubbo SPI类加载验证
     * extensionLoader.getExtension("a")  --> 取到key对应的扩展类
     * extensionLoader.getDefaultExtension() --> 取得在接口上@SPI注解指定的默认扩展类
     */
    @Test
    public void dubboSPI() {
        // 获取InfoService的 Loader 实例
        ExtensionLoader<InfoService> extensionLoader = ExtensionLoader.getExtensionLoader(InfoService.class);
        // 取得a拓展类
        InfoService infoServiceA = extensionLoader.getExtension("a");
        infoServiceA.sayHello("AAAA");
        // 取得接口上@SPI注解指定的默认拓展类（b拓展类）
        InfoService infoServiceB = extensionLoader.getDefaultExtension();
        infoServiceB.sayHello("I'm default");
    }

}

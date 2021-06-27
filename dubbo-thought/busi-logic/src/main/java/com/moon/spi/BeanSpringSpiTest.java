package com.moon.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.moon.rpc.config.ProviderConfiguration;
import com.moon.service.OrderService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Dubbo 适配类创建 - 对象属性的Spring依赖注入测试
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-2 12:54
 * @description
 */
public class BeanSpringSpiTest {

    /* Spi扩展对象依赖注入测试 */
    @Test
    public void iocSPI() {
        // 获取OrderService的 Loader 实例
        ExtensionLoader<OrderService> loader = ExtensionLoader.getExtensionLoader(OrderService.class);
        // 取得默认拓展类 OrderServiceImpl
        OrderService orderService = loader.getDefaultExtension();
        URL url = URL.valueOf("test://localhost/test?info.service=b");
        orderService.getDetail("MoonZero", url);
    }

    /* spring容器对象依赖注入测试 */
    public static void main(String[] args) {
        /* 模拟spring项目启动 */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();

        System.out.println("---------dubbo启动成功--------");
        // 获取OrderService的 Loader 实例
        ExtensionLoader<OrderService> loader = ExtensionLoader.getExtensionLoader(OrderService.class);
        // 取得拓展实现类 OrderServiceImpl2
        OrderService orderService = loader.getExtension("Moon");
        URL url = URL.valueOf("test://localhost/test?info.service=b");
        orderService.getDetail("MoonZero", url);
    }

}

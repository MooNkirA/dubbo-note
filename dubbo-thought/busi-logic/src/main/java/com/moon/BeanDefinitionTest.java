package com.moon;

import com.moon.rpc.config.BeanDefinitionTestConfiguration;
import com.moon.service.DemoService;
import com.moon.service.impl.spring.DemoServiceImpl;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring框架BeanDefinition创建示例
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-2 23:08
 * @description
 */
public class BeanDefinitionTest {

    /**
     * Spring注册Bean的过程
     */
    @Test
    public void JdkProxyTest() {
        /* 模拟spring项目启动 */
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BeanDefinitionTestConfiguration.class);
        applicationContext.start();

        // 测试原配bean功能
        DemoService configService = (DemoService) applicationContext.getBean("configService");
        configService.sayHello("原生spring bean管理功能");

        /* 测试自定义Bean注册到spring容器中 */
        // 1. 定义Bean信息
        RootBeanDefinition beanDef = new RootBeanDefinition();

        // 2. 设置BeanDefinition相关属性
        beanDef.setBeanClass(DemoServiceImpl.class);
        beanDef.setBeanClassName(DemoServiceImpl.class.getName());
        beanDef.setScope(BeanDefinition.SCOPE_SINGLETON); // 单例

        // 3. 设置bean的属性
        MutablePropertyValues propertyValues = beanDef.getPropertyValues();
        propertyValues.addPropertyValue("type", "自定义");

        // 4. 注册bean到spring容器
        applicationContext.registerBeanDefinition("demoService", beanDef);

        // 5. 测试获取刚注册的bean功能
        DemoService demoService = (DemoService) applicationContext.getBean("demoService");
        demoService.sayHello("自定义bean注册");
    }

}

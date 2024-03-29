package com.moon.dubbo.xml.test;

import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-11 9:28
 * @description
 */
public class DubboTest {

    /**
     * 回声测试：扫一遍服务是否都已就绪
     */
    @Test
    public void echoTest() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/dubbo-Test.xml");

        ctx.start();
        System.out.println("---------dubbo启动成功--------");

        String[] serviceIds = new String[]{"errorService", "userService", "orderService", "payService"};

        Object ret = null;
        for (String id : serviceIds) {
            try {
                // reference代理对象，强制转换为EchoService
                EchoService echoService = (EchoService) ctx.getBean(id);
                ret = echoService.$echo("ok");
            } catch (Exception e) {
                ret = "not ready";
            }
            System.out.println("service:" + id + ":" + ret);
        }
    }

    /**
     * 泛化调用
     * 当前项目，没有对应的接口---- service-api.OtherService
     */
    @Test
    public void other() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/dubbo-Test.xml");

        ctx.start();
        System.out.println("---------dubbo启动成功--------");

        GenericService genericService = (GenericService) ctx.getBean("unknownService");

        Object ret = genericService.$invoke("doSomething", new String[]{"java.lang.String"}, new Object[]{"moon"});
        System.out.println("调用结果：" + ret.toString());
    }

}

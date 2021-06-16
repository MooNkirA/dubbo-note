package com.moon.dubbospi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.moon.service.InfoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * SPI解决了实例的加载问题，并对实现 配置了健值对的形式进行管理
 * <p>
 * 但是，实际中会希望实例能懒加载，或者运行期再抉择
 * <p>
 * 如：InfoService的实现有三种，具体要用哪一个，在编译期未知(延迟加载考虑，只有在运行期实际需要时再加载)
 * 自适应注解Adaptive解决这个选择问题
 * <p>
 * <p>
 * 注解在类上时，直接选此类为适配类（一个接口只允许一个类标记）
 * 注解在方法上时，只为此方法生成代理逻辑（方法必须有参数为url或者参数有返回url的方法）
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-31 17:48
 * @description
 */
public class AdaptiveTest {

    /* 扩展点实现类 */
    private InfoService adaptiveExtension;

    /* 模拟请求url */
    private URL url;

    @Before
    public void before() {
        /* 获取扩展点接口的加载器对象 */
        ExtensionLoader<InfoService> loader = ExtensionLoader.getExtensionLoader(InfoService.class);
        adaptiveExtension = loader.getAdaptiveExtension();
    }

    /**
     * 如果请求的url无参数，会有两种选择方式
     * 1. 在某个实现类上有@Adaptive注解，会选择该实现类为适配类
     * 2. 如果各个实现类上面没有@Adaptive注解，Dubbo的SPI机制会以扩展接口上的@SPI("xx")注解为准，选择xx别名的实现类为适配类
     */
    @Test
    public void test1() {
        url = URL.valueOf("test://localhost/test");
    }

    /**
     * 如果各个实现类上面没有@Adaptive注解，但SPI接口上有注解@SPI("b")
     * 在接口需要调用的方法中加上注解@Adaptive
     * 此时URL中有具体的值info.service=a，则以URL为准，选择A实现
     * <p>
     * 注：参数名格式，是接口类xxXxxx的驼峰大小写拆分
     */
    @Test
    public void test2() {
        url = URL.valueOf("test://localhost/test?info.service=a");
    }

    /**
     * 各个实现类上面没有@Adaptive注解，
     * 在接口需要调用的方法中加上注解@Adaptive({"xxx"})
     * 则URL中有具体的值xxx=c，则以URL中的xxx参数为准，选择C实现类为适配类
     */
    @Test
    public void test3() {
        url = URL.valueOf("test://localhost/test?info.service=a&InfoService=c");
    }


    @After
    public void after() {
        System.out.println(adaptiveExtension.passInfo("moon", url));
    }

}

package com.moon.dubbospi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Filter;
import org.junit.After;
import org.junit.Test;

import java.util.List;

/**
 * 某此情况下，同一个接口的多个实现需要同时发挥作用，比如filter链。此时需要按条件选择一批实现类来工作
 * Activate：可以被框架中自动激活加载扩展
 * 用户通过group和value配置激活条件
 * group: 分组(筛选条件)
 * value: url中包含的key名(筛选条件)
 * order: 排序
 * <p>
 * <p>
 * 下面以filter过滤器为例
 * 1.如果需要在服务暴露时装载，那么group="provider"
 * 2.如果需要在服务引用的时候装载，那么group="consumer"
 * 3.如果想被暴露和引用时同时被装载，那么group={"consumer", "provider"}
 * 4.如果需要url中有某个特定的值才被加载，那么value={"test5", "test"}
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-31 16:06
 * @description
 */
public class ActivateTest {

    /* 获取Filter类的扩展加载器ExtensionLoader */
    private ExtensionLoader<Filter> extensionLoader = ExtensionLoader.getExtensionLoader(Filter.class);

    /* 模拟url请求协议 */
    private URL url = URL.valueOf("test://localhost/test");
    /* 加载的Filter实现类集合 */
    List<Filter> list;


    @Test
    public void testActivate1() {
        // 调用@Activate注解的group属性包含“moon”
        this.list = extensionLoader.getActivateExtension(url, "", "moon");
    }

    @Test
    public void testActivate2() {
        // 在url中增加参数“abc”，后面的值随便取，无作用
        url = url.addParameter("abc", "66666");
        /*
         * 调用@Activate注解的group属性包含“zero”
         * 如果@Activate注解有value属性，其值要为“abc”才能被加载
         */
        this.list = this.extensionLoader.getActivateExtension(url, "", "zero");
    }

    @Test
    public void testActivate3() {
        // url中有参数“abc”
        url = url.addParameter("abc", "qqqq");
        // url中指定keyw值，要使用a，去除c实现
        url = url.addParameter("myfilter", "a,-c");
        /*
         * 调用@Activate注解的group属性包含“moon”
         * 如果@Activate注解有value属性，其值要为“abc”才能被加载
         * 增加条件，增加key为a的实现类，去除key为c的实现
         */
        this.list = this.extensionLoader.getActivateExtension(url, "myfilter", "moon");
    }

    /* 测试调用结果 */
    @After
    public void testInvoke() {
        for (Filter filter : list) {
            filter.invoke(null, null);
        }
    }

}

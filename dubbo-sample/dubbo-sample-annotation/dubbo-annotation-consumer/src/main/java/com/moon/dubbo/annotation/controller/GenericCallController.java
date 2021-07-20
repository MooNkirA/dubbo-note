package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.moon.dubbo.entity.UserEntiry;
import com.moon.dubbo.service.OrderService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用泛化调用处理所有服务请求
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-18 10:47
 * @description
 */
@RestController
@RequestMapping("generic")
public class GenericCallController implements InitializingBean {

    /*
     * 用 org.apache.dubbo.rpc.service.GenericService 可以替代所有接口实现
     *  generic属性：是否缺省泛化接口，如果为泛化接口，将返回GenericService
     *  interfaceName属性：指定泛化调用的接口
     */
    // @Reference(interfaceName = "com.moon.dubbo.service.GenericCallService", generic = true)
    @Reference(generic = true)
    private OrderService orderService;
    @Autowired
    private ApplicationConfig applicationConfig;
    @Autowired
    private RegistryConfig registryConfig;
    // 引用远程服务。该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
    ReferenceConfig<GenericService> reference = new ReferenceConfig<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        reference.setApplication(applicationConfig);
        reference.setRegistry(registryConfig);
    }

    @GetMapping("order/getDetail")
    public Map<String, Object> testGeneric(@RequestParam("id") String id) {
        // 弱类型接口名
        reference.setInterface("com.moon.dubbo.service.OrderService");
        // 声明为泛化接口
        reference.setGeneric(true);

        // 用org.apache.dubbo.rpc.service.GenericService可以替代所有接口引用
        GenericService genericService = reference.get();

        /*
         *  关于方法参数：基本类型以及Date,List,Map等不需要转换，直接调用
         *  关于方法返回值：如果返回POJO将自动转成Map
         */
        Object result = genericService.$invoke("getDetail", new String[]{"java.lang.String"}, new Object[]{id});
        System.out.println(result);
        return (Map<String, Object>) result;
    }

    @PostMapping("user/regist")
    public Map<String, Object> testGeneric(@RequestBody UserEntiry user) {
        // 弱类型接口名
        reference.setInterface("com.moon.dubbo.service.UserService");
        // 声明为泛化接口
        reference.setGeneric(true);

        // 用org.apache.dubbo.rpc.service.GenericService可以替代所有接口引用
        GenericService genericService = reference.get();

        /*
         *  关于方法参数：用Map表示POJO参数
         *  关于方法返回值：如果返回POJO将自动转成Map
         */
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", user.getName());
        userMap.put("address", user.getAddress());
        userMap.put("balance", user.getBalance());
        // 注意：如果参数类型是接口，或者List等会丢失泛型，可通过class属性指定类型。
        userMap.put("class", "com.moon.dubbo.entity.UserEntiry");

        Object result = genericService.$invoke("regist", new String[]{"com.moon.dubbo.entity.UserEntiry"}, new Object[]{userMap});
        System.out.println(result);
        return (Map<String, Object>) result;
    }

}

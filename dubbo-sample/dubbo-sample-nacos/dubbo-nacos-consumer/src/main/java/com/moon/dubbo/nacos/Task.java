package com.moon.dubbo.nacos;

import com.moon.dubbo.nacos.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-14 16:20
 * @description
 */
@Component
public class Task implements CommandLineRunner {

    @DubboReference
    private HelloService helloService;

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(new Date() + " Receive result ======> " + helloService.hello("world"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.config.spring.context.annotation;

import com.alibaba.dubbo.config.AbstractConfig;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ModuleConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;

import org.springframework.context.annotation.Configuration;

/**
 * Dubbo {@link AbstractConfig Config} {@link Configuration}
 *
 * @see Configuration
 * @see EnableDubboConfigBindings
 * @see EnableDubboConfigBinding
 * @see ApplicationConfig
 * @see ModuleConfig
 * @see RegistryConfig
 * @see ProtocolConfig
 * @see MonitorConfig
 * @see ProviderConfig
 * @see ConsumerConfig
 * @since 2.5.8
 */
public class DubboConfigConfiguration {

    /**
     * Single Dubbo {@link AbstractConfig Config} Bean Binding
     */
    @EnableDubboConfigBindings({
            // 这里定义了properties文件对应的属性前缀的值，设置到相应的类中
            @EnableDubboConfigBinding(prefix = "dubbo.application", type = ApplicationConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.module", type = ModuleConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.registry", type = RegistryConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.protocol", type = ProtocolConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.monitor", type = MonitorConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.provider", type = ProviderConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.consumer", type = ConsumerConfig.class)
    })
    public static class Single {

    }

    /**
     * Multiple Dubbo {@link AbstractConfig Config} Bean Binding
     */
    @EnableDubboConfigBindings({
            @EnableDubboConfigBinding(prefix = "dubbo.applications", type = ApplicationConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.modules", type = ModuleConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.registries", type = RegistryConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.protocols", type = ProtocolConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.monitors", type = MonitorConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.providers", type = ProviderConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.consumers", type = ConsumerConfig.class, multiple = true)
    })
    public static class Multiple {

    }
}

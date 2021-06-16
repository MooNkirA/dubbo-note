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
package com.alibaba.dubbo.monitor.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.monitor.Monitor;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class DubboMonitorFactoryTest {
    private DubboMonitorFactory dubboMonitorFactory;
    @Mock
    private ProxyFactory proxyFactory;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.dubboMonitorFactory = new DubboMonitorFactory();
        this.dubboMonitorFactory.setProtocol(new DubboProtocol());
        this.dubboMonitorFactory.setProxyFactory(proxyFactory);
    }

    @Test
    public void testCreateMonitor() {
        URL urlWithoutPath = URL.valueOf("http://10.10.10.11");
        Monitor monitor = dubboMonitorFactory.createMonitor(urlWithoutPath);
        assertThat(monitor, not(nullValue()));

        URL urlWithFilterKey = URL.valueOf("http://10.10.10.11/").addParameter(Constants.REFERENCE_FILTER_KEY, "testFilter");
        monitor = dubboMonitorFactory.createMonitor(urlWithFilterKey);

        assertThat(monitor, not(nullValue()));
        ArgumentCaptor<Invoker> invokerArgumentCaptor = ArgumentCaptor.forClass(Invoker.class);
        verify(proxyFactory, atLeastOnce()).getProxy(invokerArgumentCaptor.capture());

        Invoker invoker = invokerArgumentCaptor.getValue();
        assertThat(invoker.getUrl().getParameter(Constants.REFERENCE_FILTER_KEY), containsString("testFilter"));
    }
}
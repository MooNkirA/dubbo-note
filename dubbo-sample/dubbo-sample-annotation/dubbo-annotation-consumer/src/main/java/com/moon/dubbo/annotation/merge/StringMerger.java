package com.moon.dubbo.annotation.merge;

import com.alibaba.dubbo.rpc.cluster.Merger;

import java.util.StringJoiner;

/**
 * 服务分组结果合并实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-17 10:08
 * @description
 */
public class StringMerger implements Merger<String> {

    /**
     * 定义了所有group实现类返回值的合并规则。
     * 注：此示例简单实现
     *
     * @param items
     * @return
     */
    @Override
    public String merge(String... items) {
        if (items.length == 0) {
            return null;
        }

        StringJoiner joiner = new StringJoiner("|", "[", "]");
        for (String s : items) {
            joiner.add(s);
        }

        return joiner.toString();
    }

}

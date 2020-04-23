package com.example.demo.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import com.google.common.collect.Range;

/**
 * 范围查询所使用的分片算法
 */
public class DBRangeShardAlgo implements RangeShardingAlgorithm<Long> {
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> rangeShardingValue) {
        System.out.println("范围-*-*-*-*-*-*-*-*-*-*-*---------------"+availableTargetNames);
        System.out.println("范围-*-*-*-*-*-*-*-*-*-*-*---------------"+rangeShardingValue);
        Collection<String> collect = new LinkedHashSet<>();
        Range<Long> valueRange = rangeShardingValue.getValueRange();
        for (Long i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
            	// 不分表
                //collect.add(each);

            	// 多库分表
                if (each.endsWith(i % availableTargetNames.size() + "")) {
                    collect.add(each);
                }
            }
        }
        return collect;
    }

}

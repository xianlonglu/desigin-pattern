package com.gupaoedu.config;

import io.shardingjdbc.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 范围分表 查询所使用的分片算法
 */
public class TblRangeShardAlgo implements RangeShardingAlgorithm<Long> {
	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames,
			RangeShardingValue<Long> rangeShardingValue) {
		// 不分表
		System.out.println("范围--availableTargetNames:" + availableTargetNames);
		System.out.println("范围--rangeShardingValue:" + rangeShardingValue);
		Collection<String> collect = new LinkedHashSet<>();
		for (String tbname : availableTargetNames) {
			collect.add(tbname);
		}
		return collect;
	}

}
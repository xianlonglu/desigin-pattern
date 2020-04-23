package com.example.demo.config;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ShardingRangeConfig {

	private long start;
	
	private long end;
	
	private List<String> datasourceList;

	
}


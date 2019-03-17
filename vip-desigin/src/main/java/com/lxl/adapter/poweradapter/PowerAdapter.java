package com.lxl.adapter.poweradapter;

import java.util.HashMap;
import java.util.Map;

public class PowerAdapter implements DC5 {

	private ACStrategy acStrategy;

	public PowerAdapter(ACStrategy acStrategy) {
		this.acStrategy = acStrategy;
	}

	public int outoupDC5V() {
		int adapterInput = acStrategy.outputAC();
		int adapterOutput = adapterInput / 44;
		System.out.println("使用PowerAdapter输入AC:" + adapterInput + "V,输出DC："
				+ adapterOutput + "V");
		return adapterOutput;
	}
	
	
	//适配器
	private static Map<String, ACStrategy> map = new HashMap<String, ACStrategy>();
	static {
		map.put(ACKey.ACKey220, new AC220());
		map.put(ACKey.ACKey360, new AC360());
	}
	public PowerAdapter() {
	}
	
	public static ACStrategy getACStrategy(String key){
		if(map.containsKey(key)){
			return map.get(key);
		} else {
			return map.get(ACKey.NON_PROMOTION);
		}
	}
	private interface ACKey{
		String ACKey220 = "ac220";
		String ACKey360 = "ac360";
		String NON_PROMOTION = "ac220";
	}
	
}

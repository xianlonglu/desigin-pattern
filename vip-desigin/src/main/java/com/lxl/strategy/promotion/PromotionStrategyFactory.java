package com.lxl.strategy.promotion;

import java.util.HashMap;
import java.util.Map;

public class PromotionStrategyFactory {
	private static Map<String, PromotionStrategy> map = new HashMap<String, PromotionStrategy>();
	static {
		map.put(PromotionKey.COUPON, new CouponStrategy());
		map.put(PromotionKey.GROUPBUY, new GroupbuyStrategy());
		map.put(PromotionKey.NON_PROMOTION, new EmptyStrategy());
	}
	private PromotionStrategyFactory() {
		
	}
	
	public static PromotionStrategy getPromotionStrategy(String promotionKey){
		if(map.containsKey(promotionKey)){
			return map.get(promotionKey);
		} else {
			return map.get(PromotionKey.NON_PROMOTION);
		}
	}
	private interface PromotionKey{
		String COUPON = "coupon";
		String GROUPBUY = "groupbuy";
		String NON_PROMOTION = COUPON;
	}
}

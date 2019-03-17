package com.lxl.strategy.promotion;

public class EmptyStrategy implements PromotionStrategy {

	@Override
	public void doPromotion() {
		System.out.println("返现，返回支付账户");
	}

}

package com.lxl.strategy.promotion;

public class CashbackStrategy implements PromotionStrategy {

	@Override
	public void doPromotion() {
		System.out.println("没有优惠");
	}

}

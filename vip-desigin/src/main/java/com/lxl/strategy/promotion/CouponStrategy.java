package com.lxl.strategy.promotion;

public class CouponStrategy implements PromotionStrategy {

	@Override
	public void doPromotion() {
		System.out.println("领取优惠劵，直接减免");
	}

}

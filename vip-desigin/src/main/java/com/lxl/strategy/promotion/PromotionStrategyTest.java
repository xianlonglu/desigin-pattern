package com.lxl.strategy.promotion;

public class PromotionStrategyTest {
	public static void main(String[] args) {

		// PromotionActivity activity618 = new PromotionActivity(new
		// CouponStrategy());
		// PromotionActivity activity1111 = new PromotionActivity(new
		// CouponStrategy());
		// activity618.execute();
		// activity1111.execute();

		// PromotionActivity activity = null;
		// String promotionKey = "coupon";
		// if ("coupon".equals(promotionKey)) {
		// activity = new PromotionActivity(new CouponStrategy());
		// } else if ("groupbuy".equals(promotionKey)) {
		// activity = new PromotionActivity(new GroupbuyStrategy());
		// } // ……
		// activity.execute();

		PromotionActivity activity1 = new PromotionActivity(
				PromotionStrategyFactory.getPromotionStrategy("coupon"));
		activity1.execute();
		
		PromotionStrategyFactory.getPromotionStrategy("coupon").doPromotion();
	}
}

package com.lxl.strategy.promotion;

public class PromotionActivity {
	PromotionStrategy promotionStrategy;

	public PromotionActivity(PromotionStrategy promotionStrategy) {
		this.promotionStrategy = promotionStrategy;
	}

	public void execute() {
		promotionStrategy.doPromotion();
	}
}

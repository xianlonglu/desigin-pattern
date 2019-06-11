package com.gupaoedu.vip;

/**
 * 支付
 */

@RpcService(IPaymentService.class)
public class PaymentServiceImpl implements IPaymentService{
    @Override
    public String doPay() {
        System.out.println("执行doPay方法");
        return "doPay SUCCESS";
    }
}

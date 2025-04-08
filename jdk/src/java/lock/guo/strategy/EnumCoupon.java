package lock.guo.strategy;

/**
 * @description: 优惠券枚举
 * @author: guofengbo
 * @date: 2020-05-12 17:23
 **/
public enum EnumCoupon {

    Coupon1(1),
    Coupon2(2),
    Coupon3(3),

    ;

    private Integer couponType;

    EnumCoupon(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getCouponType() {
        return couponType;
    }
}

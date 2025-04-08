package lock.guo.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 优惠券工厂
 * @author: guofengbo
 * @date: 2020-05-12 17:22
 **/
public class CouponFactory {

    private static Coupon1 coupon1 = new Coupon1();
    private static Coupon2 coupon2 = new Coupon2();
    private static Coupon3 coupon3 = new Coupon3();

    private void allCouponMap() {
        Map result = new HashMap();
        result.put(EnumCoupon.Coupon1.getCouponType(), coupon1);
        result.put(EnumCoupon.Coupon2.getCouponType(), coupon2);
        result.put(EnumCoupon.Coupon3.getCouponType(), coupon3);
    }

}

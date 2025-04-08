package lock.guo;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author : fengbo.guo
 * @date : 2024-08-27 14:51
 * @Description :
 */
@Slf4j
public class Test {


    public static void main(String[] args) {

        BigDecimal amount1 = new BigDecimal("0.00033").multiply(new BigDecimal("709749")).setScale(2, RoundingMode.DOWN);
        BigDecimal amount2 = new BigDecimal("0.00033").multiply(new BigDecimal("709945")).setScale(2, RoundingMode.DOWN);

        System.out.println("amount1 : " + amount1 + "amount2 :" + amount2);
    }
}


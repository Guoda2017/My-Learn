package object.guo;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * @description:
 * @author: guofengbo
 * @create: 2020-02-29 20:02
 **/
@Slf4j
public class Start {

    public static void main(String[] args) throws ParseException {

        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        BigDecimal plainPrice = new BigDecimal("0.9601");
        plainPrice = new BigDecimal(plainPrice.stripTrailingZeros().toPlainString());

        System.out.println(new BigDecimal(decimalFormat.format(plainPrice)));
    }


    private static BigDecimal get() {
        return BigDecimal.ONE;
    }

}

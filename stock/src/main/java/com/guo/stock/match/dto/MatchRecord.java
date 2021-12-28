package com.guo.stock.match.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 11:20
 * @Description :
 */
@Slf4j
@Data
public class MatchRecord {

    public final BigDecimal price;
    public final BigDecimal amount;
    public final Order takerOrder;
    public final Order makerOrder;

}

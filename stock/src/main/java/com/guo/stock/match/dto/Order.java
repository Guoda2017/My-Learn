package com.guo.stock.match.dto;

import com.guo.stock.match.enums.DirectionEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 10:59
 * @Description :
 */
@Slf4j
@Data
public class Order {

    /**
     * 序号
     */
    private final Long sequenced;

    /**
     * 方向
     */
    private final DirectionEnum directionEnum;

    /**
     * 价格
     */
    private final BigDecimal price;

    /**
     * 数量
     */
    private BigDecimal amount;
}

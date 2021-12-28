package com.guo.stock.match.service;

import com.guo.stock.match.dto.MatchResult;
import com.guo.stock.match.dto.Order;
import com.guo.stock.match.dto.OrderBook;
import com.guo.stock.match.enums.DirectionEnum;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 11:15
 * @Description : 一个完整的撮合引擎包含一个买盘、一个卖盘和一个最新成交价（初始值为0）
 */
@Slf4j
public class MatchService {

    /**
     * 买入订单
     */
    public final OrderBook buyBook = new OrderBook(DirectionEnum.BUY);

    /**
     * 赎回订单
     */
    public final OrderBook sellBook = new OrderBook(DirectionEnum.SELL);

    /**
     * 最新市场价
     */
    public BigDecimal marketPrice = BigDecimal.ZERO;

//    public MatchResult processOrder(Order order) {
//        switch (order.getDirectionEnum()) {
//            case BUY:
//                // 买单与sellBook匹配，最后放入buyBook:
//                return processOrder(order, this.sellBook, this.buyBook);
//            case SELL:
//                // 卖单与buyBook匹配，最后放入sellBook:
//                return processOrder(order, this.buyBook, this.sellBook);
//            default:
//                throw new IllegalArgumentException("Invalid direction.");
//        }
//    }

//    private MatchResult processOrder(Order takerOrder, OrderBook makerBook, OrderBook anotherBook) {
//        MatchResult matchResult = new MatchResult(takerOrder);
//        for (; ; ) {
//            Order makerOrder = makerBook.getFirst();
//            if (makerOrder == null) {
//                // 对手盘不存在:
//                break;
//            }
//            if (takerOrder.getDirectionEnum() == DirectionEnum.BUY && takerOrder.getPrice().compareTo(makerOrder.price) < 0) {
//                // 买入订单价格比卖盘第一档价格低:
//                break;
//            } else if (takerOrder.getDirectionEnum() == DirectionEnum.SELL && takerOrder.getPrice().compareTo(makerOrder.price) > 0) {
//                // 卖出订单价格比卖盘第一档价格高:
//                break;
//            }
//            // 以Maker价格成交:
//            this.marketPrice = makerOrder.getPrice();
//            // 成交数量为两者较小值:
//            BigDecimal matchedAmount = takerOrder.getAmount().min(makerOrder.getAmount());
//            // 成交记录:
//            matchResult.add(makerOrder.getPrice(), matchedAmount, makerOrder);
//            // 更新成交后的订单数量:
//            takerOrder.getAmount() = takerOrder.getAmount().subtract(matchedAmount);
//            makerOrder.getAmount() = makerOrder.getAmount().subtract(matchedAmount);
//            // 对手盘完全成交后，从订单簿中删除:
//            if (makerOrder.getAmount().signum() == 0) {
//                makerBook.remove(makerOrder);
//            }
//            // Taker订单完全成交后，退出循环:
//            if (takerOrder.getAmount().signum() == 0) {
//                break;
//            }
//        }
//        // Taker订单未完全成交时，放入订单簿:
//        if (takerOrder.getAmount().signum() > 0) {
//            anotherBook.add(takerOrder);
//        }
//        return matchResult;
//    }

}

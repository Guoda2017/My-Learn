package com.guo.stock.match.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 11:07
 * @Description : 在证券交易中，使用List会导致两个致命问题：
 * <p>
 * 插入新的订单时，必须从头扫描List<Order>，以便在合适的地方插入Order，平均耗时O(N)；
 * 取消订单时，也必须从头扫描List<Order>，平均耗时O(N)。
 * 更好的方法是使用红黑树，它是一种自平衡的二叉排序树，插入和删除的效率都是O(logN)，对应的Java类是TreeMap。
 * <p>
 * 所以我们定义OrderBook的结构就是一个TreeMap<OrderKey, Order>，它的排序根据OrderKey决定。由业务规则可知，负责排序的OrderKey只需要sequenceId和price即可：
 */
@Slf4j
@Data
public class OrderKey {

    /**
     * 序号
     */
    public final long sequenceId;

    /**
     * 价格
     */
    public final BigDecimal price;

}

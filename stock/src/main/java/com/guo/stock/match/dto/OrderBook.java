package com.guo.stock.match.dto;

import com.guo.stock.match.enums.DirectionEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 11:09
 * @Description :
 */
@Slf4j
@Data
public class OrderBook {

    private static final Comparator<OrderKey> SORT_SELL = new Comparator<OrderKey>() {
        @Override
        public int compare(OrderKey o1, OrderKey o2) {
            // 价格低在前:
            int cmp = o1.price.compareTo(o2.price);
            // 时间早在前:
            return cmp == 0 ? Long.compare(o1.sequenceId, o2.sequenceId) : cmp;
        }
    };

    private static final Comparator<OrderKey> SORT_BUY = new Comparator<OrderKey>() {
        @Override
        public int compare(OrderKey o1, OrderKey o2) {
            // 价格高在前:
            int cmp = o2.price.compareTo(o1.price);
            // 时间早在前:
            return cmp == 0 ? Long.compare(o1.sequenceId, o2.sequenceId) : cmp;
        }
    };

    /**
     * 方向
     */
    public final DirectionEnum directionEnum;
    /**
     * 排序树
     */
    public final TreeMap<OrderKey, Order> book;

    public OrderBook(DirectionEnum directionEnum) {
        this.directionEnum = directionEnum;
        this.book = new TreeMap<>(directionEnum == DirectionEnum.BUY ? SORT_BUY : SORT_SELL);
    }

    public Order getFirst() {
        return this.book.isEmpty() ? null : this.book.firstEntry().getValue();
    }

    public boolean remove(Order order) {
        return this.book.remove(new OrderKey(order.getSequenced(), order.getPrice())) != null;
    }

    public boolean add(Order order) {
        return this.book.put(new OrderKey(order.getSequenced(), order.getPrice()), order) == null;
    }

}

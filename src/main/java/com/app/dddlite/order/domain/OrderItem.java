package com.app.dddlite.order.domain;

import com.app.dddlite.catalog.domain.Product;
import com.app.dddlite.common.model.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @Setter
    private Order order;

    private Money orderPrice;

    private int count;

    private OrderItem(Product product, Money orderPrice, int count) {
        this.product = product;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderItem createOrderItem(Product product, Money orderPrice, int count) {
        OrderItem orderItem = new OrderItem(product, orderPrice, count);
        product.removeStock(count);
        return orderItem;
    }

    public void cancel() {
        getProduct().addStock(count);
    }

    /**
     * 주문상품 전체 가격 조회
     */
    public Money getTotalPrice() {
        return getOrderPrice().times(getCount());
    }
}

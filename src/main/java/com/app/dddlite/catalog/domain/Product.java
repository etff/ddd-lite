package com.app.dddlite.catalog.domain;

import com.app.dddlite.catalog.exception.NotEnoughStockException;
import com.app.dddlite.common.model.Money;
import com.app.dddlite.config.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product extends BaseEntity {
    private static final String NOT_ENOUGH_STOCK_ERROR = "Need more stock";

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"))
    private Set<CategoryId> categoryIds;

    @Embedded
    private Money price;

    private String detail;

    private int stockQuantity;

    /**
     * stock 증가
     * @param quantity
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int stockQuantity) {
        int restStock = this.stockQuantity - stockQuantity;

        if (restStock < 0) {
            throw new NotEnoughStockException(NOT_ENOUGH_STOCK_ERROR);
        }

        this.stockQuantity = restStock;
    }

}



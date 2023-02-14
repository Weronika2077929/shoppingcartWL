package kata.supermarket.basket;

import kata.supermarket.discount.DiscountType;

import java.math.BigDecimal;

public interface Item {
    BigDecimal price();
    DiscountType getDiscountType();
}

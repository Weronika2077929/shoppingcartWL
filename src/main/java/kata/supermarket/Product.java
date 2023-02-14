package kata.supermarket;

import kata.supermarket.basket.Item;
import kata.supermarket.basket.ItemByUnit;
import kata.supermarket.discount.DiscountType;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Product {

    public static final int ONE_UNIT = 1;
    private final BigDecimal pricePerUnit;
    private final DiscountType discount;

    public Product(BigDecimal pricePerUnit, DiscountType discount) {
        if (discount.isNotApplicableByUnit()) {
            throw new IllegalArgumentException();
        }
        this.pricePerUnit = pricePerUnit;
        this.discount = discount;
    }

    public Item oneOf() {
        return new ItemByUnit(this, ONE_UNIT);
    }

    public Item manyOf(int quantity) {
        return new ItemByUnit(this, quantity);
    }
}

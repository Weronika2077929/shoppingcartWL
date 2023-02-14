package kata.supermarket;

import kata.supermarket.basket.Item;
import kata.supermarket.basket.ItemByWeight;
import kata.supermarket.discount.DiscountType;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class WeighedProduct {

    private final BigDecimal pricePerKilo;
    private final DiscountType discount;

    public WeighedProduct(BigDecimal pricePerKilo, DiscountType discount) {
        if (discount.isNotApplicableByWeight()) {
            throw new IllegalArgumentException();
        }
        this.pricePerKilo = pricePerKilo;
        this.discount = discount;
    }

    public BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}

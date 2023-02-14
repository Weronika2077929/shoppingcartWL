package kata.supermarket.basket;

import kata.supermarket.WeighedProduct;
import kata.supermarket.discount.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@AllArgsConstructor
@Getter
public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, HALF_UP);
    }

    public DiscountType getDiscountType() {
        return product.getDiscount();
    }

}

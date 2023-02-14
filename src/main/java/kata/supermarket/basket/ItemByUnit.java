package kata.supermarket.basket;

import kata.supermarket.Product;
import kata.supermarket.discount.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@AllArgsConstructor
@Getter
public class ItemByUnit implements Item {

    private final Product product;
    private Integer quantity;

    public BigDecimal price() {
        return product.getPricePerUnit().multiply(BigDecimal.valueOf(quantity)).setScale(2, HALF_UP);
    }

    public BigDecimal pricePerUnit() {
        return product.getPricePerUnit();
    }

    public DiscountType getDiscountType() {
        return product.getDiscount();
    }
}

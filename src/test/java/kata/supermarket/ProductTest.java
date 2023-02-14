package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static kata.supermarket.discount.DiscountType.NO_DISCOUNT;
import static kata.supermarket.discount.DiscountType.ONE_KILO_HALF_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new Product(price, NO_DISCOUNT).oneOf().price());
    }

    @Test
    void singleItemDefaultDiscountTest() {
        final BigDecimal price = new BigDecimal("2.49");
        final Product product = new Product(price, NO_DISCOUNT);
        assertEquals(NO_DISCOUNT, product.getDiscount());
    }

    @Test
    void product_givenWeightedDiscount_throwsException() {
        final BigDecimal price = new BigDecimal("2.49");
        assertThrows(IllegalArgumentException.class, () -> new Product(price, ONE_KILO_HALF_PRICE));
    }

}
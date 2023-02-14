package kata.supermarket.discount;

import kata.supermarket.basket.Item;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Arrays.asList;

public class DiscountCalculator {

    private final List<Discount> discounts;

    public DiscountCalculator() {
        discounts = asList(new DiscountBuyOneGetOne(),
                new DiscountOneKiloHalf(),
                new DiscountBuyTwoForPound(),
                new DiscountBuyThreeForTwo());
    }

    public BigDecimal applyDiscounts(List<Item> itemList) {
        return discounts.stream()
                .map(discount -> discount.discount(itemList))
                .reduce(BigDecimal::add)
                .orElse(ZERO)
                .setScale(2, HALF_UP);
    }

}

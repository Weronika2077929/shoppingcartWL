package kata.supermarket.basket;

import kata.supermarket.discount.DiscountCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Collections.unmodifiableList;

public class Basket {
    private final List<Item> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;
        private final DiscountCalculator discountCalc;

        TotalCalculator() {
            this.items = items();
            this.discountCalc = new DiscountCalculator();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(ZERO)
                    .setScale(2, HALF_UP);
        }

        private BigDecimal discounts() {
            return discountCalc.applyDiscounts(this.items);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}

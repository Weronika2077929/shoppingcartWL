package kata.supermarket.discount;

import kata.supermarket.basket.Item;
import kata.supermarket.basket.ItemByWeight;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static kata.supermarket.discount.DiscountType.ONE_KILO_HALF_PRICE;

public class DiscountOneKiloHalf implements Discount {

    private static final BigDecimal THRESHOLD = BigDecimal.ONE;
    public static final BigDecimal FIFTY_PERCENT_DISCOUNT = new BigDecimal("0.5");

    @Override
    public BigDecimal discount(List<Item> itemList) {

        return getApplicableItems(itemList)
                .map(Item::price)
                .reduce(BigDecimal::add)
                .orElse(ZERO)
                .multiply(FIFTY_PERCENT_DISCOUNT)
                .setScale(2, HALF_UP);
    }

    private Stream<ItemByWeight> getApplicableItems(List<Item> itemList) {
        return itemList.stream()
                .filter(i -> i.getDiscountType().equals(ONE_KILO_HALF_PRICE))
                .filter(i -> ((ItemByWeight) i).getWeightInKilos().compareTo(THRESHOLD) >= 0)
                .map(i -> (ItemByWeight) i);
    }

}

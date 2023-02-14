package kata.supermarket.discount;

import kata.supermarket.basket.Item;
import kata.supermarket.basket.ItemByUnit;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static kata.supermarket.discount.DiscountType.BUY_ONE_GET_ONE;

public class DiscountBuyOneGetOne implements Discount {

    public static final int PAIR = 2;

    @Override
    public BigDecimal discount(List<Item> itemList) {

        return getApplicableItems(itemList)
                .map(i -> i.pricePerUnit().multiply(BigDecimal.valueOf(Math.floorDiv(i.getQuantity(), PAIR))))
                .reduce(BigDecimal::add)
                .orElse(ZERO)
                .setScale(2, HALF_UP);
    }

    private Stream<ItemByUnit> getApplicableItems(List<Item> itemList) {

        return itemList.stream()
                .filter(i -> i.getDiscountType().equals(BUY_ONE_GET_ONE))
                .filter(i -> ((ItemByUnit) i).getQuantity() >= PAIR)
                .map(i -> (ItemByUnit) i);
    }
}

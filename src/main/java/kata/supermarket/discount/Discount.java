package kata.supermarket.discount;

import kata.supermarket.basket.Item;

import java.math.BigDecimal;
import java.util.List;

public interface Discount {

        BigDecimal discount(List<Item> itemList);

}

package kata.supermarket.discount;

import kata.supermarket.basket.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.*;
import static java.util.Collections.singletonList;
import static kata.supermarket.TestUtils.*;
import static kata.supermarket.discount.DiscountType.*;
import static org.junit.jupiter.api.Assertions.*;

class DiscountBuyThreeForTwoTest {

    @DisplayName("Basket discounts Buy Three For Two...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discount_givenItemsWithBuyThreeForTwo_returnsCorrectDelta(String description, String expectedDiscount, Iterable<Item> items) {
        final List<Item> itemList = new ArrayList<>();
        items.forEach(itemList::add);
        final DiscountBuyThreeForTwo discountOffer = new DiscountBuyThreeForTwo();
        assertEquals(new BigDecimal(expectedDiscount), discountOffer.discount(itemList));
    }

    static Stream<Arguments> discount_givenItemsWithBuyThreeForTwo_returnsCorrectDelta() {
        return Stream.of(
                aSingleItemPricedPerUnit(),
                twoItemsPricedPerUnit(),
                threeItemsPricedPerUnit(),
                fourItemsPricedPerUnit(),
                fiveItemsPricedPerUnit(),
                sixItemsPricedPerUnit()
        );
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.00",
                singleton(aPackOfDigestives(1, BUY_THREE_FOR_TWO)));
    }

    private static Arguments twoItemsPricedPerUnit() {
        return Arguments.of("two per unit items with buy three for two applied", "0.00",
                singletonList(aPackOfDigestives(2, BUY_THREE_FOR_TWO)));
    }

    private static Arguments threeItemsPricedPerUnit() {
        return Arguments.of("three per unit items with buy three for two applied", "1.50",
                singletonList(aPackOfDigestives(3, BUY_THREE_FOR_TWO)));
    }

    private static Arguments fourItemsPricedPerUnit() {
        return Arguments.of("three per unit items with buy three for two applied", "1.50",
                singletonList(aPackOfDigestives(4, BUY_THREE_FOR_TWO)));
    }

    private static Arguments fiveItemsPricedPerUnit() {
        return Arguments.of("five per unit items with buy three for two applied", "1.50",
                singletonList(aPackOfDigestives(5, BUY_THREE_FOR_TWO)));
    }

    private static Arguments sixItemsPricedPerUnit() {
        return Arguments.of("six per unit items with buy three for two applied", "3.00",
                singletonList(aPackOfDigestives(6, BUY_THREE_FOR_TWO)));
    }

}
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

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static kata.supermarket.TestUtils.aPackOfDigestives;
import static kata.supermarket.TestUtils.aPackOfSuperChocolateDigestive;
import static kata.supermarket.discount.DiscountType.BUY_ONE_GET_ONE;
import static org.junit.jupiter.api.Assertions.*;

class DiscountBuyOneGetOneTest {

    @DisplayName("Basket discounts Buy One Get One Free...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discount_givenItemsWithBuyOneGetOneDiscount_returnsCorrectDelta(String description, String expectedDiscount, Iterable<Item> items) {
        final List<Item> itemList = new ArrayList<>();
        items.forEach(itemList::add);
        final DiscountBuyOneGetOne discountOffer = new DiscountBuyOneGetOne();
        assertEquals(new BigDecimal(expectedDiscount), discountOffer.discount(itemList));
    }

    static Stream<Arguments> discount_givenItemsWithBuyOneGetOneDiscount_returnsCorrectDelta() {
        return Stream.of(
                aSingleItemPricedPerUnit(),
                twoItemsPricedPerUnit(),
                threeItemsPricedPerUnit(),
                fourItemsPricedPerUnit(),
                multipleItemsPricedPerUnit()
        );
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.00", singleton(aPackOfDigestives(1, BUY_ONE_GET_ONE)));
    }

    private static Arguments twoItemsPricedPerUnit() {
        return Arguments.of("two per unit items with buy one get one free applied", "1.50",
                asList(aPackOfDigestives(2, BUY_ONE_GET_ONE)));
    }

    private static Arguments threeItemsPricedPerUnit() {
        return Arguments.of("three per unit items with buy one get one free applied", "1.50",
                asList(aPackOfDigestives(3, BUY_ONE_GET_ONE)));
    }

    private static Arguments fourItemsPricedPerUnit() {
        return Arguments.of("three per unit items with buy one get one free applied", "3.00",
                asList(aPackOfDigestives(4, BUY_ONE_GET_ONE)));
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple per unit items with buy one get one free applied", "5.50",
                asList(aPackOfDigestives(2, BUY_ONE_GET_ONE), aPackOfSuperChocolateDigestive(5, BUY_ONE_GET_ONE)));
    }

}
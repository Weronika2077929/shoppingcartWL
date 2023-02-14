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
import static kata.supermarket.TestUtils.aPackOfSkinnyDigestives;
import static kata.supermarket.discount.DiscountType.BUY_TWO_FOR_POUND;
import static org.junit.jupiter.api.Assertions.*;

class DiscountBuyTwoForPoundTest {

    @DisplayName("Basket discounts Two For One Pound ...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discount_givenItemsWithTwoForOnePound_returnsCorrectDelta(String description, String expectedDiscount, Iterable<Item> items) {
        final List<Item> itemList = new ArrayList<>();
        items.forEach(itemList::add);
        final DiscountBuyTwoForPound discountOffer = new DiscountBuyTwoForPound();
        assertEquals(new BigDecimal(expectedDiscount), discountOffer.discount(itemList));
    }

    static Stream<Arguments> discount_givenItemsWithTwoForOnePound_returnsCorrectDelta() {
        return Stream.of(
                aSingleItemPricedPerUnit(),
                twoItemsPricedPerUnit(),
                threeItemsPricedPerUnit(),
                fourItemsPricedPerUnit(),
                multipleItemsPricedPerUnit()
        );
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.00", singleton(aPackOfSkinnyDigestives(1, BUY_TWO_FOR_POUND)));
    }

    private static Arguments twoItemsPricedPerUnit() {
        return Arguments.of("two per unit items with buy one get one free applied", "0.50",
                asList(aPackOfSkinnyDigestives(2, BUY_TWO_FOR_POUND)));
    }

    private static Arguments threeItemsPricedPerUnit() {
        return Arguments.of("three per unit items with buy one get one free applied", "0.50",
                asList(aPackOfSkinnyDigestives(3, BUY_TWO_FOR_POUND)));
    }

    private static Arguments fourItemsPricedPerUnit() {
        return Arguments.of("four per unit items with buy one get one free applied", "1.00",
                asList(aPackOfSkinnyDigestives(4, BUY_TWO_FOR_POUND)));
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("unit items with buy one get one free applied", "3.00",
                asList(aPackOfSkinnyDigestives(4, BUY_TWO_FOR_POUND), aPackOfDigestives(3, BUY_TWO_FOR_POUND)));
    }

}
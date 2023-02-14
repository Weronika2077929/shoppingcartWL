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
import static java.util.Collections.singletonList;
import static kata.supermarket.TestUtils.*;
import static kata.supermarket.discount.DiscountType.*;
import static org.junit.jupiter.api.Assertions.*;

class DiscountCalculatorTest {
    @DisplayName("Testing various discounts applied on basket using discount calc ...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discount_givenItemsAndVariousDiscounts_ReturnsCorrectTotalDelta(String description, String expectedTotalDiscount, Iterable<Item> items) {
        final List<Item> itemList = new ArrayList<>();
        items.forEach(itemList::add);
        final DiscountCalculator discountCalculator = new DiscountCalculator();

        assertEquals(new BigDecimal(expectedTotalDiscount), discountCalculator.applyDiscounts(itemList));
    }

    static Stream<Arguments> discount_givenItemsAndVariousDiscounts_ReturnsCorrectTotalDelta() {
        return Stream.of(
                itemPricedByWeight(),
                multipleItemsPricedPerUnit(),
                mixedItems(),
                itemPricedByWeightNoDiscount(),
                itemPricedPerUnitNoDiscount(),
                multipleItemsPricedPerUnitVariousDiscounts()
        );
    }

    private static Arguments mixedItems() {
        return Arguments.of("mixed items with weight discount and buy one get one free", "10.49",
                asList(twoKilogramsOfAmericanSweets(),
                        aPackOfDigestives(2, BUY_ONE_GET_ONE),
                        aPackOfSuperChocolateDigestive(5, BUY_ONE_GET_ONE)));
    }

    private static Arguments itemPricedByWeight() {
        return Arguments.of("weighed item discount applied per weight", "4.99",
                singletonList(twoKilogramsOfAmericanSweets())
        );
    }

    private static Arguments itemPricedByWeightNoDiscount() {
        return Arguments.of("weighed item no discount", "0.00",
                singletonList(twoKilogramsOfAmericanSweetsNoDiscount())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple per unit items discount buy one get one free applied", "5.50",
                asList(aPackOfDigestives(3, BUY_ONE_GET_ONE),
                        aPackOfSuperChocolateDigestive(4, BUY_ONE_GET_ONE)));
    }

    private static Arguments multipleItemsPricedPerUnitVariousDiscounts() {
        return Arguments.of("multiple per unit items with various discounts", "6.00",
                asList(aPackOfDigestives(3, BUY_THREE_FOR_TWO),
                        aPackOfSuperChocolateDigestive(4, BUY_ONE_GET_ONE),
                        aPackOfSkinnyDigestives(2, BUY_TWO_FOR_POUND)));
    }

    private static Arguments itemPricedPerUnitNoDiscount() {
        return Arguments.of("multiple per unit items no discount", "0.00",
                singletonList(aPackOfDigestivesNoDiscount(4)));
    }

}
package kata.supermarket.basket;

import kata.supermarket.discount.DiscountType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static kata.supermarket.TestUtils.*;
import static kata.supermarket.discount.DiscountType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                mixedItemsWithDiscounts(),
                mixedItemsWithMixedDiscounts()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "1.99",
                asList(aPackOfDigestives(1, NO_DISCOUNT), aPintOfMilkNoDiscount()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", singleton(aPintOfMilkNoDiscount()));
    }

    private static Arguments mixedItemsWithDiscounts() {
        return Arguments.of("various per unit and weighted items with discounts", "9.09",
                asList( aPackOfDigestives(1, BUY_ONE_GET_ONE),
                        aPackOfSuperChocolateDigestive(2, BUY_ONE_GET_ONE),
                        twoHundredGramsOfPickAndMix(),
                        twoKilogramsOfAmericanSweets()));
    }

    private static Arguments mixedItemsWithMixedDiscounts() {
        return Arguments.of("various per unit and weighted items with discounts", "12.99",
                asList( aPackOfDigestives(3, DiscountType.BUY_THREE_FOR_TWO),
                        aPackOfSuperChocolateDigestive(4, BUY_ONE_GET_ONE),
                        aPackOfSkinnyDigestives(2, BUY_TWO_FOR_POUND),
                        twoKilogramsOfAmericanSweets()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", emptyList());
    }

}
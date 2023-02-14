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
import static kata.supermarket.TestUtils.twoHundredGramsOfPickAndMix;
import static kata.supermarket.TestUtils.twoKilogramsOfAmericanSweets;
import static org.junit.jupiter.api.Assertions.*;

class DiscountOneKiloHalfTest {

    @DisplayName("basket discounts above One Kilo Half Price test...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discount_givenItemsWithOneKiloHalfPrice_returnsCorrectDelta(String description, String expectedDiscount, Iterable<Item> items) {
        final List<Item> itemList = new ArrayList<>();
        items.forEach(itemList::add);
        final DiscountOneKiloHalf discountOffer = new DiscountOneKiloHalf();
        BigDecimal expected = new BigDecimal(expectedDiscount);
        assertEquals(expected, discountOffer.discount(itemList));
    }

    static Stream<Arguments> discount_givenItemsWithOneKiloHalfPrice_returnsCorrectDelta() {
        return Stream.of(
                multipleWeightedItemsOneAboveThreshold(),
                twoWeightedItemsAboveThreshold()
        );
    }

    private static Arguments multipleWeightedItemsOneAboveThreshold() {
        return Arguments.of("multiple weighed items with one half kilo discount ", "4.99",
                asList(twoKilogramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments twoWeightedItemsAboveThreshold() {
        return Arguments.of("two items above or equal to 1 kg", "9.98",
                asList(twoKilogramsOfAmericanSweets(), twoKilogramsOfAmericanSweets())
        );
    }

}
package kata.supermarket;

import kata.supermarket.basket.Item;
import kata.supermarket.discount.DiscountType;

import java.math.BigDecimal;

import static kata.supermarket.discount.DiscountType.NO_DISCOUNT;
import static kata.supermarket.discount.DiscountType.ONE_KILO_HALF_PRICE;

public class TestUtils {

    public static Item aPintOfMilkNoDiscount() {
        return new Product(new BigDecimal("0.49"), NO_DISCOUNT).oneOf();
    }

    public static Item aPackOfSkinnyDigestives(int quantity, DiscountType discount) {
        return new Product(new BigDecimal("0.75"), discount).manyOf(quantity);
    }

    public static Item aPackOfDigestives(int quantity, DiscountType discount) {
        return new Product(new BigDecimal("1.50"), discount).manyOf(quantity);
    }

    public static Item aPackOfDigestivesNoDiscount(int quantity) {
        return new Product(new BigDecimal("1.50"), NO_DISCOUNT).manyOf(quantity);
    }

    public static Item aPackOfSuperChocolateDigestive(int quantity, DiscountType discount) {
        return new Product(new BigDecimal("2.00"), discount).manyOf(quantity);
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"), ONE_KILO_HALF_PRICE);
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"), ONE_KILO_HALF_PRICE);
    }

    private static WeighedProduct aKiloOfAmericanSweetsNoDiscount() {
        return new WeighedProduct(new BigDecimal("4.99"), NO_DISCOUNT);
    }

    public static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    public static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    public static Item twoKilogramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal("2"));
    }

    public static Item twoKilogramsOfAmericanSweetsNoDiscount() {
        return aKiloOfAmericanSweetsNoDiscount().weighing(new BigDecimal("2"));
    }

}

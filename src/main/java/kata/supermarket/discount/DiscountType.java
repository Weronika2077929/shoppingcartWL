package kata.supermarket.discount;

import static java.util.Arrays.asList;

public enum DiscountType {
    BUY_ONE_GET_ONE,
    BUY_THREE_FOR_TWO,
    BUY_TWO_FOR_POUND,
    ONE_KILO_HALF_PRICE,
    NO_DISCOUNT;

    public Boolean isNotApplicableByWeight() {
        return !asList(ONE_KILO_HALF_PRICE, NO_DISCOUNT).contains(this);
    }

    public Boolean isNotApplicableByUnit() {
        return !asList(BUY_ONE_GET_ONE, BUY_THREE_FOR_TWO, BUY_TWO_FOR_POUND, NO_DISCOUNT).contains(this);
    }
}

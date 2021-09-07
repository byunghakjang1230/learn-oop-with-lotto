package org.byunghakjang1230.lotto.domain;

public class Price {
    private final int price;

    public Price(final int price) {
        validatePriceIsPositive(price);
        this.price = price;
    }

    private void validatePriceIsPositive(final int price) {
        if (price < 0) {
            throw new IllegalArgumentException("금액으로 음수가 입력될 수 없습니다.");
        }
    }

    public int calculateBuyQuantityBy(final int pricePerOne) {
        return this.price / pricePerOne;
    }
}

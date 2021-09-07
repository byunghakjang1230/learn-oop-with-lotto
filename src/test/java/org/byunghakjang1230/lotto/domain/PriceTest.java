package org.byunghakjang1230.lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Price 도메인 테스트 코드")
class PriceTest {

    @Test
    @DisplayName("마이너스 금액 입력 오류 확인")
    void numberValue_validate() {
        // when - then
        assertThatThrownBy(() -> new Price(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액으로 음수가 입력될 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"900:0", "1000:1", "2500:2", "3900:3", "4000:4"}, delimiter = ':')
    @DisplayName("구매가능 수량 계산")
    void calculateBuyQuantity(int price, int result) {
        // when
        int quantity = new Price(price).calculateBuyQuantityBy(1000);

        // then
        assertThat(quantity).isEqualTo(result);
    }
}

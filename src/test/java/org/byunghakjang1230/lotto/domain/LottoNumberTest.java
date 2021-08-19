package org.byunghakjang1230.lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또 번호 도메인 테스트")
class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또번호 범위 바깥일 경우 예외")
    void lottoNumber_range_exception(int number) {
        // then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1~45 사이의 숫자만 가능합니다.");
    }
}

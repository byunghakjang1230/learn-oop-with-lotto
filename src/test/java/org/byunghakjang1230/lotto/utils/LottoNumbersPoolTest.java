package org.byunghakjang1230.lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.byunghakjang1230.lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또번호풀 유틸 테스트")
class LottoNumbersPoolTest {
    @Test
    @DisplayName("전체 로또번호 객체 생성 개수 확인")
    void lottoNumbers_size_check() {
        // when
        List<LottoNumber> allLottoNumbers = LottoNumbersPool.getAllLottoNumbers();

        // then
        assertThat(allLottoNumbers.size()).isEqualTo(LottoNumber.END_NUMBER);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 45})
    @DisplayName("로또번호 객체 꺼네기")
    void find_lottoNumber(int number) {
        // when
        LottoNumber lottoNumber = LottoNumbersPool.getLottoNumber(number);

        // then
        assertThat(lottoNumber.isSameNumber(number)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호 범위 바깥의 숫자 조회 시 예외처리")
    void find_lottoNumber_with_outOfRange_exception(int number) {
        // then
        assertThatThrownBy(() -> LottoNumbersPool.getLottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("생성되지 않는 로또번호입니다.");
    }
}

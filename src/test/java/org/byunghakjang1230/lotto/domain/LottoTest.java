package org.byunghakjang1230.lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.byunghakjang1230.lotto.exception.DuplicateLottoNumbersException;
import org.byunghakjang1230.lotto.utils.LottoNumbersPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또 도메인 테스트")
class LottoTest {

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    @DisplayName("로또 번호 길이 예외처리")
    void lotto_size_exception(String textWinningNumber) {
        // given
        List<LottoNumber> lottoNumbers = Arrays.stream(textWinningNumber.split(","))
                .map(Integer::parseInt)
                .map(LottoNumbersPool::getLottoNumber)
                .collect(Collectors.toList());

        // then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 로또번호가 6개가 아닙니다.");
    }

    @Test
    @DisplayName("로또 번호 중복 번호 예외처리")
    void lotto_duplicate_exception() {
        // given
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator() {
            @Override
            public List<LottoNumber> generateLottoNumbers() {
                return toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
            }
        };

        // then
        assertThatThrownBy(() -> new Lotto(lottoNumbersGenerator.toLottoNumbers(Arrays.asList(1, 2, 3, 3, 4, 5))))
                .isInstanceOf(DuplicateLottoNumbersException.class)
                .hasMessage("중복되는 로또번호가 존재합니다.");
    }

    @Test
    @DisplayName("일치하는 로또번호")
    void match_count() {
        // given
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator() {
            @Override
            public List<LottoNumber> generateLottoNumbers() {
                return toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
            }
        };
        Lotto lotto = new Lotto(lottoNumbersGenerator.toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbersGenerator.toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));

        // when
        int matchNumberCount = lotto.getMatchNumberCount(winningNumbers);

        // then
        assertThat(matchNumberCount).isEqualTo(6);
    }
}

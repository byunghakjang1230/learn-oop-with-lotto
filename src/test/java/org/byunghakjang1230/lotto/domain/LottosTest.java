package org.byunghakjang1230.lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.byunghakjang1230.lotto.utils.TypeConvertor;

@DisplayName("로또 일급 컬랙션 테스트")
class LottosTest {
    private LottoNumbersGenerator lottoNumbersGenerator;

    @BeforeEach
    void setUp() {
        lottoNumbersGenerator = () -> TypeConvertor.toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("통계데이터 생성 테스트")
    void make_statistic_data() {
        // given
        LottoMachine lottoMachine = new LottoMachine(lottoNumbersGenerator);
        Lottos lottos = lottoMachine.createLottosAutomatically(1000);
        WinningNumbers winningNumbers = WinningNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6));

        // when
        WinningResultStatistics winningResultStatistics = lottos.makeWinningResultStatistics(winningNumbers);

        // then
        assertThat(winningResultStatistics.isEmpty()).isFalse();
    }
}

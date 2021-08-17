package org.byunghakjang1230.lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("로또 일급 컬랙션 테스트")
class LottosTest {
    private LottoNumbersGenerator lottoNumbersGenerator;

    @BeforeEach
    void setUp() {
        lottoNumbersGenerator = new LottoNumbersGenerator() {
            @Override
            public List<LottoNumber> generateLottoNumbers() {
                return toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
            }
        };
    }

    @Test
    @DisplayName("통계데이터 생성 테스트")
    void make_statistic_data() {
        // given
        LottoMachine lottoMachine = new LottoMachine(lottoNumbersGenerator);
        Lottos lottos = lottoMachine.createLottosAuto(1000);
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbersGenerator.toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));

        // when
        WinningResultStatistics winningResultStatistics = lottos.makeWinningResultStatistics(winningNumbers);

        // then
        assertThat(winningResultStatistics.isEmpty()).isFalse();
    }
}

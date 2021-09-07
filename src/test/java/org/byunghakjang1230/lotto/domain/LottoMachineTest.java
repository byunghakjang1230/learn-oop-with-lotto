package org.byunghakjang1230.lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.byunghakjang1230.lotto.utils.TypeConvertor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("로또기계 도메인 테스트")
class LottoMachineTest {
    private LottoNumbersGenerator lottoNumbersGenerator;

    @BeforeEach
    void setUp() {
        lottoNumbersGenerator = () -> TypeConvertor.toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("로또 생성")
    void create_lottos() {
        // given
        LottoMachine lottoMachine = new LottoMachine(lottoNumbersGenerator);

        // when
        Lottos lottos = lottoMachine.createLottosAutomatically(new Price(3000));

        // then
        assertThat(lottos.size()).isEqualTo(3);
    }
}

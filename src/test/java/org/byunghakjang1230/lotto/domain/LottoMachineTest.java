package org.byunghakjang1230.lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

@DisplayName("로또기계 도메인 테스트")
class LottoMachineTest {
    @TestFactory
    @DisplayName("로또 생성")
    List<DynamicTest> create_lottos() {
        // given
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator() {
            @Override
            public List<LottoNumber> generateLottoNumbers() {
                return toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
            }
        };
        LottoMachine lottoMachine = new LottoMachine(lottoNumbersGenerator);

        // when
        List<Lotto> lottos = lottoMachine.createLottosAuto(3000);

        // then
        return Arrays.asList(
                dynamicTest("로또목록 건수 확인", () -> assertThat(lottos.size()).isEqualTo(3)),
                dynamicTest("로또목록 번호 확인", () -> {
                    for (Lotto lotto : lottos) {
                        assertThat(lotto.matchNumberCount(lottoNumbersGenerator.toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)))).isEqualTo(6);
                    }
                })
        );
    }
}

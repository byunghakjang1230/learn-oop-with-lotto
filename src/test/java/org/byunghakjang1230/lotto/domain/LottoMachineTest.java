package org.byunghakjang1230.lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또기계 도메인 테스트")
class LottoMachineTest {
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

    @TestFactory
    @DisplayName("로또 생성")
    List<DynamicTest> create_lottos() {
        // given
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

    @Test
    @DisplayName("당첨번호 생성 확인")
    void to_winningLottoNumbers() {
        // given
        LottoMachine lottoMachine = new LottoMachine(lottoNumbersGenerator);

        // when
        List<LottoNumber> winningLottoNumbers = lottoMachine.toWinningLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));

        // when
        assertThat(winningLottoNumbers).size().isEqualTo(6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void to_sinningLottoNumbers_size_exception(String textWinningNumber) {
        // given
        LottoMachine lottoMachine = new LottoMachine(lottoNumbersGenerator);
        List<Integer> winningNumber = Arrays.stream(textWinningNumber.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // then
        assertThatThrownBy(() -> lottoMachine.toWinningLottoNumbers(winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 로또번호가 6개가 아닙니다.");
    }
}

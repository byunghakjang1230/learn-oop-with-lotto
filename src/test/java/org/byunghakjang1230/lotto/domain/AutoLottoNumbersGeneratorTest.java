package org.byunghakjang1230.lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("자동 로또번호 생성기 도메인 테스트")
class AutoLottoNumbersGeneratorTest {
    @Test
    @DisplayName("자동 로또번호 생성기가 만든 번호 중복여부 확인")
    void not_duplicate_numbers() {
        // given
        AutoLottoNumbersGenerator generator = new AutoLottoNumbersGenerator();

        // when
        List<LottoNumber> lottoNumbers = generator.generateLottoNumbers();

        // then
        assertThat(new HashSet<>(lottoNumbers).size()).isEqualTo(lottoNumbers.size());
    }
}

package org.byunghakjang1230.lotto.constant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("로또 순위 정책 상수 테스트")
class LottoRankingPolicyTest {
    @ParameterizedTest
    @CsvSource(value = {"3:false:FIFTH", "4:false:FOURTH", "5:false:THIRD", "5:true:SECOND", "6:false:FIRST"}, delimiter = ':')
    @DisplayName("로또 순위 설정")
    void find_rank(int matchCount, boolean isMatchedBonusNumber, LottoRankingPolicy rank) {
        // then
        assertThat(LottoRankingPolicy.findLottoRankBy(matchCount, isMatchedBonusNumber)).isEqualTo(rank);
    }
}

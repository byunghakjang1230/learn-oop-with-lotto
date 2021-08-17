package org.byunghakjang1230.lotto.constant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("로또 순위 정책 상수 테스트")
class LottoRankingPolicyTest {
    @ParameterizedTest
    @CsvSource(value = {"3:FOURTH", "4:THIRD", "5:SECOND", "6:FIRST"}, delimiter = ':')
    void find_rank(int matchCount, LottoRankingPolicy rank) {
        // then
        assertThat(LottoRankingPolicy.findLottoRankByMatchCount(matchCount)).isEqualTo(rank);
    }
}

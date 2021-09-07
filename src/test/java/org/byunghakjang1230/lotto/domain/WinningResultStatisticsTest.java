package org.byunghakjang1230.lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashMap;
import java.util.Map;

import org.byunghakjang1230.lotto.constant.LottoRankingPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("로또 당첨 통계 결과 생성")
class WinningResultStatisticsTest {
    @Test
    @DisplayName("통계정보 데이터 생성 확인")
    void create_statistics_data() {
        // given
        Map<LottoRankingPolicy, Integer> lottoCounts = new HashMap<>();
        lottoCounts.put(LottoRankingPolicy.FOURTH, 1);

        // when
        WinningResultStatistics winningResultStatistics = WinningResultStatistics.of(lottoCounts, 5000);

        // then
        Long totalPrizeMoney = LottoRankingPolicy.FOURTH.multiplyPrizeMoneyBy(1);
        assertAll(
                () -> assertThat(winningResultStatistics.getTotalPrizeMoney()).isEqualTo(totalPrizeMoney),
                () -> assertThat(winningResultStatistics.getMatchCountBy(LottoRankingPolicy.FOURTH)).isEqualTo(1),
                () -> assertThat(winningResultStatistics.getProfitRate()).isEqualTo(10.00)
        );
    }
}

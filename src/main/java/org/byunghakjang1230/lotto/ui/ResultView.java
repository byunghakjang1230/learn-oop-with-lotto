package org.byunghakjang1230.lotto.ui;

import static java.lang.System.out;

import org.byunghakjang1230.lotto.constant.LottoRankingPolicy;
import org.byunghakjang1230.lotto.domain.Lottos;
import org.byunghakjang1230.lotto.domain.WinningResultStatistics;

public class ResultView {
    public void showLottoNumbers(Lottos lottos) {
        for (int i = 0; i < lottos.size(); i++) {
            out.println(lottos.getLotto(i).toStringLottoNumbersWith("[", "]", ", "));
        }
        out.println();
    }

    public void showWinningResultStatistics(WinningResultStatistics statistics) {
        out.println("당첨 통계\n---------");
        for (LottoRankingPolicy rank : LottoRankingPolicy.orderValuesWithoutOutOfRank()) {
            out.println(rank.getMatchCount() + "개 일치" + getBlankOrBonusNumberText(rank) + "(" + rank.getPrizeMoney() + ") - " + statistics.getMatchCountBy(rank) + "개");

        }
        out.println("총 수익률을 " + statistics.getProfitRate() + "입니다." + getLossText(statistics));
    }

    private String getBlankOrBonusNumberText(LottoRankingPolicy rank) {
        if (LottoRankingPolicy.SECOND.equals(rank)) {
            return ", 보너스 볼 일치";
        }
        return " ";
    }

    private String getLossText(WinningResultStatistics statistics) {
        if (statistics.isLoss()) {
            return "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }
        return "";
    }
}

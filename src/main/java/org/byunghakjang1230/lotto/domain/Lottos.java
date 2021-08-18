package org.byunghakjang1230.lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.byunghakjang1230.lotto.constant.LottoRankingPolicy;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int size() {
        return this.lottos.size();
    }

    public WinningResultStatistics makeWinningResultStatistics(WinningNumbers winningNumbers) {
        Map<LottoRankingPolicy, Integer> lottoCounts = new EnumMap<>(LottoRankingPolicy.class);
        for (Lotto lotto : this.lottos) {
            int count = lotto.getMatchNumberCount(winningNumbers);
            LottoRankingPolicy rank = LottoRankingPolicy.findLottoRankByMatchCount(count);
            lottoCounts.put(rank, lottoCounts.getOrDefault(rank, 0) + 1);
        }
        return WinningResultStatistics.of(lottoCounts, getTotalPrice());
    }

    private int getTotalPrice() {
        return this.lottos.size() * Lotto.LOTTO_PRICE_PER_ONE;
    }
}

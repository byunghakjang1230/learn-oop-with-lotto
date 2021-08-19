package org.byunghakjang1230.lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.byunghakjang1230.lotto.constant.LottoRankingPolicy;

public class Lottos {
    private static final int START_INDEX_NUMBER = 0;
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int size() {
        return this.lottos.size();
    }

    public Lotto getLotto(int index) {
        validateIndexRange(index);
        return this.lottos.get(index);
    }

    public WinningResultStatistics makeWinningResultStatistics(WinningNumbers winningNumbers) {
        Map<LottoRankingPolicy, Integer> lottoCounts = new EnumMap<>(LottoRankingPolicy.class);
        for (Lotto lotto : this.lottos) {
            int count = lotto.getMatchNumberCount(winningNumbers);
            LottoRankingPolicy rank = LottoRankingPolicy.findLottoRankBy(count);
            lottoCounts.put(rank, lottoCounts.getOrDefault(rank, 0) + 1);
        }
        return WinningResultStatistics.of(lottoCounts, getTotalPrice());
    }

    private void validateIndexRange(int index) {
        if (index < START_INDEX_NUMBER || index >= size()) {
            throw new IllegalArgumentException("올바른 인덱스 범위가 아닙니다.");
        }
    }

    private int getTotalPrice() {
        return this.lottos.size() * Lotto.LOTTO_PRICE_PER_ONE;
    }
}

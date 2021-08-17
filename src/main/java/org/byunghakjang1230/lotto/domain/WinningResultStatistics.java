package org.byunghakjang1230.lotto.domain;

import java.util.Arrays;
import java.util.Map;

import org.byunghakjang1230.lotto.constant.LottoRankingPolicy;

public class WinningResultStatistics {
    private static final int ONE_PERCENT = 100;
    private Map<LottoRankingPolicy, Integer> rankCounts;
    private Long totalPrizeMoney;
    private Double profitRate;

    private WinningResultStatistics(final Map<LottoRankingPolicy, Integer> rankCounts, final Long totalPrizeMoney,
                                    final Double profitRate) {
        this.rankCounts = rankCounts;
        this.totalPrizeMoney = totalPrizeMoney;
        this.profitRate = profitRate;
    }

    public static WinningResultStatistics of(final Map<LottoRankingPolicy, Integer> rankCounts, final int totalPrice) {
        Long totalPrizeMoney = calculateTotalPrizeMoney(rankCounts);
        return new WinningResultStatistics(rankCounts, totalPrizeMoney, calculateProfitRate(totalPrizeMoney, totalPrice));
    }

    public Long getTotalPrizeMoney() {
        return this.totalPrizeMoney;
    }

    public Double getProfitRate() {
        return profitRate;
    }

    public boolean isEmpty() {
        return rankCounts.isEmpty();
    }

    public int getMatchCountBy(final LottoRankingPolicy rank) {
        return this.rankCounts.getOrDefault(rank, 0);
    }

    private static long calculateTotalPrizeMoney(final Map<LottoRankingPolicy, Integer> rankCounts) {
        return Arrays.stream(LottoRankingPolicy.values())
                .mapToLong(rank -> rank.multiplyPrizeMoney(rankCounts.getOrDefault(rank, 0)))
                .sum();
    }

    private static double calculateProfitRate(final Long totalPrizeMoney, final int totalPrice) {
        return (totalPrizeMoney.doubleValue() * ONE_PERCENT / totalPrice) / ONE_PERCENT;
    }
}

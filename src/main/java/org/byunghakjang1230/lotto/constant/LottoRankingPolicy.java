package org.byunghakjang1230.lotto.constant;

import java.util.Arrays;

public enum LottoRankingPolicy {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    OUT_OF_RANK(0, 0);

    private int matchCount;
    private int prizeMoney;

    LottoRankingPolicy(int matchCount, int prizeMoney) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public Long multiplyPrizeMoney(int matchCount) {
        return (long)(this.prizeMoney * matchCount);
    }

    public static LottoRankingPolicy findLottoRankByMatchCount(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.isSameMatchCount(matchCount))
                .findAny()
                .orElse(OUT_OF_RANK);
    }

    private boolean isSameMatchCount(int matchCount) {
        return this.matchCount == matchCount;
    }
}

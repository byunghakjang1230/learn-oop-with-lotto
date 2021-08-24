package org.byunghakjang1230.lotto.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum LottoRankingPolicy {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    OUT_OF_RANK(0, 0);

    private int matchCount;
    private int prizeMoney;

    LottoRankingPolicy(int matchCount, int prizeMoney) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public static List<LottoRankingPolicy> orderValuesWithoutOutOfRank() {
        LottoRankingPolicy[] values = values();
        Arrays.sort(values, Collections.reverseOrder());
        return Arrays.stream(values)
                .filter(lottoRankingPolicy -> !lottoRankingPolicy.equals(OUT_OF_RANK))
                .collect(Collectors.toList());
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public Long multiplyPrizeMoneyBy(int matchCount) {
        return (long) this.prizeMoney * (long) matchCount;
    }

    public static LottoRankingPolicy findLottoRankBy(int matchCount, boolean isMatchedBonusNumber) {
        if (isSecond(matchCount, isMatchedBonusNumber)) {
            return SECOND;
        }
        return findLottoRankWithOutSecondBy(matchCount);
    }

    public static LottoRankingPolicy findLottoRankWithOutSecondBy(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> !SECOND.equals(rank))
                .filter(rank -> rank.isSameMatchCount(matchCount))
                .findAny()
                .orElse(OUT_OF_RANK);
    }

    private static boolean isSecond(int matchCount, boolean isMatchedBonusNumber) {
        return SECOND.isSameMatchCount(matchCount) && isMatchedBonusNumber;
    }

    private boolean isSameMatchCount(int matchCount) {
        return this.matchCount == matchCount;
    }
}

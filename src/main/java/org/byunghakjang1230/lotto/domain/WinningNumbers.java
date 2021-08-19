package org.byunghakjang1230.lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

import org.byunghakjang1230.lotto.exception.DuplicateLottoNumbersException;
import org.byunghakjang1230.lotto.utils.TypeConvertor;

public class WinningNumbers implements LottoNumbers {
    private final List<LottoNumber> winningLottoNumbers;

    private WinningNumbers(List<LottoNumber> winningLottoNumbers) {
        this.winningLottoNumbers = winningLottoNumbers;
    }

    public static WinningNumbers of(List<Integer> winningNumbers) {
        validateWinningLottoNumbersSize(winningNumbers);
        validateDuplicateWinningLottoNumbers(winningNumbers);
        return new WinningNumbers(TypeConvertor.toLottoNumbers(winningNumbers));
    }

    @Override
    public int getMatchNumberCount(LottoNumbers lottoNumbers) {
        return (int)this.winningLottoNumbers.stream()
                .filter(lottoNumbers::isContain)
                .count();
    }

    @Override
    public boolean isContain(LottoNumber lottoNumber) {
        return this.winningLottoNumbers.stream()
                .anyMatch(lottoNumber::equals);
    }

    @Override
    public String toStringLottoNumbersWith(String prefix, String postfix, String delimiter) {
        StringJoiner lottoNumber = new StringJoiner(delimiter);
        for (LottoNumber winningNumber : winningLottoNumbers) {
            lottoNumber.add(winningNumber.toString());
        }
        return prefix + lottoNumber.toString() + postfix;
    }

    private static void validateDuplicateWinningLottoNumbers(List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != winningNumbers.size()) {
            throw new DuplicateLottoNumbersException("중복되는 당첨번호가 존재합니다.");
        }
    }

    private static void validateWinningLottoNumbersSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != Lotto.LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("입력된 로또번호가 6개가 아닙니다.");
        }
    }
}

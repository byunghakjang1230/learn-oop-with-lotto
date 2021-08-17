package org.byunghakjang1230.lotto.domain;

import java.util.HashSet;
import java.util.List;

import org.byunghakjang1230.lotto.exception.DuplicateLottoNumbersException;

public class WinningNumbers {
    private final List<LottoNumber> winningNumbers;

    public WinningNumbers(List<LottoNumber> winningNumbers) {
        validateWinningLottoNumbersSize(winningNumbers);
        validateDuplicateWinningLottoNumbers(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validateDuplicateWinningLottoNumbers(List<LottoNumber> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != winningNumbers.size()) {
            throw new DuplicateLottoNumbersException("중복되는 당첨번호가 존재합니다.");
        }
    }

    private void validateWinningLottoNumbersSize(List<LottoNumber> winningNumbers) {
        if (winningNumbers.size() != Lotto.LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("입력된 로또번호가 6개가 아닙니다.");
        }
    }

    public int getMatchNumberCount(Lotto lotto) {
        return lotto.matchNumberCount(this.winningNumbers);
    }
}

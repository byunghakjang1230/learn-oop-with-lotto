package org.byunghakjang1230.lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.byunghakjang1230.lotto.exception.DuplicateLottoNumbersException;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        validateDuplicateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public int matchNumberCount(final List<LottoNumber> lottoNumbers) {
        List<LottoNumber> resultLottoNumbers = new ArrayList<>(lottoNumbers);
        resultLottoNumbers.retainAll(this.lottoNumbers);
        return lottoNumbers.size();
    }

    private void validateDuplicateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (new HashSet<>(lottoNumbers).size() != lottoNumbers.size()) {
            throw new DuplicateLottoNumbersException("중복되는 로또번호가 존재합니다.");
        }
    }
}

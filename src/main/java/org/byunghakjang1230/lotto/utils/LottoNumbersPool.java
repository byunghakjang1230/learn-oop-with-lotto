package org.byunghakjang1230.lotto.utils;

import java.util.ArrayList;
import java.util.List;

import org.byunghakjang1230.lotto.domain.LottoNumber;

public class LottoNumbersPool {
    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();
    static {
        for (int i = LottoNumber.START_NUMBER; i <= LottoNumber.END_NUMBER; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    public static List<LottoNumber> getAllLottoNumbers() {
        return lottoNumbers;
    }

    public static LottoNumber getLottoNumber(int number) {
        return lottoNumbers.stream()
                .filter(lottoNumber -> lottoNumber.isSameNumber(number))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("생성되지 않는 로또번호입니다."));
    }
}

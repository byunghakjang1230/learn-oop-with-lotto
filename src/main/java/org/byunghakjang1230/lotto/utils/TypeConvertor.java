package org.byunghakjang1230.lotto.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.byunghakjang1230.lotto.domain.LottoNumber;

public class TypeConvertor {

    private TypeConvertor() {
        throw new IllegalStateException("Utility Class");
    }

    public static List<LottoNumber> toLottoNumbers(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}

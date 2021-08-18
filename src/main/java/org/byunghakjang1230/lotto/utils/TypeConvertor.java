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

    public static int toInteger(String textNumber) {
        try {
            return Integer.parseInt(textNumber);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자로 변환할 수 없는 문자입니다. input text : " + textNumber);
        }
    }
}

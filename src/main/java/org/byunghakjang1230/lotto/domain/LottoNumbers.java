package org.byunghakjang1230.lotto.domain;

public interface LottoNumbers {
    boolean isContain(LottoNumber lottoNumber);
    String toStringLottoNumbersWith(String prefix, String postfix, String delimiter);
}

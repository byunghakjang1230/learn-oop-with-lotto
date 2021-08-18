package org.byunghakjang1230.lotto.domain;

public interface LottoNumbers {
    int getMatchNumberCount(LottoNumbers lottoNumbers);
    boolean isContain(LottoNumber lottoNumber);
}

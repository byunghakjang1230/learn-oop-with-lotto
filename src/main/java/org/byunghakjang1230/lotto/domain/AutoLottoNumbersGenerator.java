package org.byunghakjang1230.lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.byunghakjang1230.lotto.utils.LottoNumbersPool;

public class AutoLottoNumbersGenerator implements LottoNumbersGenerator{
    @Override
    public List<LottoNumber> generateLottoNumbers() {
        List<LottoNumber> lottoNumbers = LottoNumbersPool.getAllLottoNumbers();
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.stream()
                .limit(6)
                .collect(Collectors.toList());
    }
}

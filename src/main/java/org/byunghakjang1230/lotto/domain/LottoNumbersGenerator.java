package org.byunghakjang1230.lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.byunghakjang1230.lotto.utils.LottoNumbersPool;

public interface LottoNumbersGenerator {
    default List<LottoNumber> toLottoNumbers(List<Integer> lottoNumbers){
        return lottoNumbers.stream()
                .map(LottoNumbersPool::getLottoNumber)
                .collect(Collectors.toList());
    }

    List<LottoNumber> generateLottoNumbers();
}

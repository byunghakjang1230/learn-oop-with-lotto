package org.byunghakjang1230.lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.byunghakjang1230.lotto.utils.LottoNumbersPool;

public class LottoMachine {
    public static final int PRICE_PER_ONE_LOTTO = 1000;
    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoMachine(final LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbersGenerator = lottoNumbersGenerator;
    }

    public List<Lotto> createLottosAuto(final int price) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < calculateNumberOfBuy(price); i++) {
            lottos.add(new Lotto(lottoNumbersGenerator.generateLottoNumbers()));
        }
        return lottos;
    }

    public List<LottoNumber> toWinningLottoNumbers(List<Integer> winningNumbers) {
        validateWinningLottoNumbersSize(winningNumbers);
        return winningNumbers.stream()
                .map(LottoNumbersPool::getLottoNumber)
                .collect(Collectors.toList());
    }

    private void validateWinningLottoNumbersSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != Lotto.LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("입력된 로또번호가 6개가 아닙니다.");
        }
    }

    private int calculateNumberOfBuy(int price) {
        return price / PRICE_PER_ONE_LOTTO;
    }
}

package org.byunghakjang1230.lotto.domain;

import java.util.Objects;

public class LottoNumber {
    public static final int START_NUMBER = 1;
    public static final int END_NUMBER = 45;
    private final int lottoNumber;

    public LottoNumber(int number) {
        validateLottoNumberRange(number);
        this.lottoNumber = number;
    }

    public boolean isSameNumber(int number) {
        return this.lottoNumber == number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public String toString() {
        return String.valueOf(this.lottoNumber);
    }

    private void validateLottoNumberRange(int number) {
        if (number < START_NUMBER || number > END_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이의 숫자만 가능합니다.");
        }
    }

    public int lessThan(LottoNumber lottoNumber) {
        return this.lottoNumber - lottoNumber.lottoNumber;
    }
}

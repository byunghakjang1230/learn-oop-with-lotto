package org.byunghakjang1230.lotto.exception;

public class DuplicateLottoNumbersException extends RuntimeException {
    public DuplicateLottoNumbersException() {
        super("중복되는 로또번호가 존재합니다.");
    }

    public DuplicateLottoNumbersException(String message) {
        super(message);
    }
}

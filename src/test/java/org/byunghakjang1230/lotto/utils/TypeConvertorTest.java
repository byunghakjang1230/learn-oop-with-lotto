package org.byunghakjang1230.lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("타입변환 유틸 테스트")
class TypeConvertorTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "s", "f", "d", ""})
    @DisplayName("문자를 숫자로 변환")
    void toInteger_exception(String textNumber) {
        // then
        assertThatThrownBy(() -> TypeConvertor.toInteger(textNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자로 변환할 수 없는 문자입니다. input text : " + textNumber);
    }

    @ParameterizedTest
    @CsvSource(value = {"3:3", "10:10"}, delimiter = ':')
    @DisplayName("문자를 숫자로 변환")
    void toInteger(String textNumber, int resultNumber) {
        // when
        int number = TypeConvertor.toInteger(textNumber);

        // then
        assertThat(number).isEqualTo(resultNumber);
    }
}

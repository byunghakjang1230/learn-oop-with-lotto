package org.byunghakjang1230.lotto.ui;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.byunghakjang1230.lotto.domain.Lotto;
import org.byunghakjang1230.lotto.utils.TypeConvertor;

public class InputView {
    private static final String SPLIT_REGEX = ",";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int showInputPriceComment() {
        out.println("구입금액을 입력해 주세요.");
        int price = TypeConvertor.toInteger(scanner.nextLine());
        out.println((price / Lotto.LOTTO_PRICE_PER_ONE) + "개를 구매했습니다.");
        return price;
    }

    public List<Integer> showInputLastWeekWinningNumbers() {
        out.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] textWinningNumber = scanner.nextLine().split(SPLIT_REGEX);
        out.println();
        return toIntegerCollection(textWinningNumber);
    }

    private List<Integer> toIntegerCollection(String[] textLastWinningNumber) {
        return Arrays.stream(textLastWinningNumber)
                .map(TypeConvertor::toInteger)
                .collect(Collectors.toList());
    }

    public int showInputLastWeekWinningBonusNumber() {
        out.println("보너스 볼을 입력해 주세요.");
        return TypeConvertor.toInteger(scanner.nextLine());
    }
}

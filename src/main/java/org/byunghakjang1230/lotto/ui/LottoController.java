package org.byunghakjang1230.lotto.ui;

import java.util.Scanner;

import org.byunghakjang1230.lotto.domain.*;
import org.byunghakjang1230.lotto.utils.TypeConvertor;

public class LottoController {
    private final LottoMachine lottoMachine;
    private final InputView inputView;
    private final ResultView resultView;

    public LottoController() {
        this.lottoMachine = new LottoMachine(new AutoLottoNumbersGenerator());
        this.inputView = new InputView(new Scanner(System.in));
        this.resultView = new ResultView();
    }

    public void runLotto() {
        Lottos lottos = this.lottoMachine.createLottosAuto(inputView.showInputPriceComment());
        resultView.showLottoNumbers(lottos);
        WinningResultStatistics winningResultStatistics = lottos.makeWinningResultStatistics(
                new WinningNumbers(TypeConvertor.toLottoNumbers(inputView.showInputLastWeekWinningNumbers())));
        resultView.showWinningResultStatistics(winningResultStatistics);
    }
}

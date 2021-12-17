package com.lgm.study.chapter2.act1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    void createTest() {
        final Lotto lotteryNumA = Lotto.newLotteryAInstance();
        final Lotto lotteryNumB = Lotto.newLotteryBInstance();

        Assertions.assertNotNull(lotteryNumA);
        Assertions.assertInstanceOf(LotteryNumA.class, lotteryNumA);
        Assertions.assertNotNull(lotteryNumB);
        Assertions.assertInstanceOf(LotteryNumB.class, lotteryNumB);
    }

    @Test
    void instanceTest() {
        final Lotto lotteryNumA = Lotto.getLotteryAInstance();
        final Lotto lotteryNumB = Lotto.getLotteryBInstance();

        Assertions.assertNotNull(lotteryNumA);
        Assertions.assertInstanceOf(LotteryNumA.class, lotteryNumA);
        Assertions.assertNotNull(lotteryNumB);
        Assertions.assertInstanceOf(LotteryNumB.class, lotteryNumB);

        final Lotto fromA = Lotto.from("A");
        final Lotto fromB = Lotto.from("B");

        Assertions.assertNotNull(fromA);
        Assertions.assertInstanceOf(LotteryNumA.class, fromA);
        Assertions.assertNotNull(fromB);
        Assertions.assertInstanceOf(LotteryNumB.class, fromB);
    }

    @Test
    void printLotteryATest() {
        final Lotto lotteryNumA = Lotto.getLotteryAInstance();

        Assertions.assertNotNull(lotteryNumA);
        Assertions.assertInstanceOf(LotteryNumA.class, lotteryNumA);

        lotteryNumA.printLotteryNum();
    }

    @Test
    void printLotteryBTest() {
        final Lotto lotteryNumB = Lotto.getLotteryBInstance();

        Assertions.assertNotNull(lotteryNumB);
        Assertions.assertInstanceOf(LotteryNumB.class, lotteryNumB);

        lotteryNumB.printLotteryNum();
    }
}
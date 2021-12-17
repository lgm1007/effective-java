package com.lgm.study.chapter2.act1;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LotteryNumB extends Lotto {
    private static final int SIZE = 6;

    @Override
    public void printLotteryNum() {
        List<Integer> numberList = Lotto.numberList;
        Collections.shuffle(numberList);
        numberList.stream().limit(SIZE).collect(Collectors.toList())
                .forEach(i -> System.out.print(i + " "));
    }
}

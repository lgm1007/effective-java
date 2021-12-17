package com.lgm.study.chapter2.act1;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public abstract class Lotto {
    private static int MIN_NUMBER = 1;
    private static int MAX_NUMBER = 45;

    public static List<Integer> numberList = new LinkedList<>();
    // 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
    public static Lotto LOTTERYA = new LotteryNumA();
    public static Lotto LOTTERYB = new LotteryNumB();

    static {
        IntStream.range(MIN_NUMBER, MAX_NUMBER)
                .forEach(i -> numberList.add(i));
    }

    public abstract void printLotteryNum();

    // 입력 매개변수에 따라 다른 클래스 객체를 반환할 수 있다.
    // 반환 타입의 하위 타입을 반환할 수 있다.
    public static Lotto from (String flag) {
        return flag.equals("A") ? LOTTERYA : LOTTERYB;
    }

    // 이름을 가질 수 있다.
    public static Lotto getLotteryAInstance() {
        return LOTTERYA;
    }

    public static Lotto getLotteryBInstance() {
        return LOTTERYB;
    }

    public static Lotto newLotteryAInstance() {
        return new LotteryNumA();
    }

    public static Lotto newLotteryBInstance() {
        return new LotteryNumB();
    }

}

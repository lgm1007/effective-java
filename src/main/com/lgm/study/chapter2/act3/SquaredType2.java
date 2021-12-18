package com.lgm.study.chapter2.act3;

public class SquaredType2 {
    public static final SquaredType2 INSTANCE = new SquaredType2();
    private SquaredType2() {}

    // 정적 팩토리 메서드를 public static 멤버로 제공
    public static SquaredType2 getInstance() {
        return INSTANCE;
    }

    public void square (int n) {
        System.out.println(n*n);
    }
}

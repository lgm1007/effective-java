package com.lgm.study.chapter2.act3;

public class SquaredType1 {
    // public static 멤버가 final 필드인 방식
    public static final SquaredType1 INSTANCE = new SquaredType1();
    private SquaredType1() {}

    public void square (int n) {
        System.out.println(n*n);
    }
}

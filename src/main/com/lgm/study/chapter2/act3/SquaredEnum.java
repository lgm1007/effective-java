package com.lgm.study.chapter2.act3;

// 열거 타입으로 싱글턴 만들기
public enum SquaredEnum {
    INSTANCE;

    public void square (int n) {
        System.out.println(n * n);
    }
}

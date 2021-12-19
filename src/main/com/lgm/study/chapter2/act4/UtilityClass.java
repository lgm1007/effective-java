package com.lgm.study.chapter2.act4;

public class UtilityClass {
    // 인스턴스화를 막기 위한 private 생성자
    private UtilityClass() {
        throw new AssertionError();
    }

    public void printCall() {
        System.out.println("UtilityClass 호출");
    }
}

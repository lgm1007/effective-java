package com.lgm.study.chapter2.act5;

import java.util.Objects;

public class DefectTester {
    private final FuncType type;

    // 생성자에 필요한 자원 넘겨줌
    public DefectTester(FuncType tester) {
        this.type = (FuncType) Objects.requireNonNull(tester);
    }

    public void runTest() {
        this.type.runFunc();
    }
}

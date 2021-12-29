package com.lgm.study.chapter3.act5;

import java.util.Comparator;
import java.util.Objects;

public class Machine implements Comparable<Machine> {
    protected int code;
    protected char type;
    protected Operator operator;

    public Machine(int code, char type, Operator operator) {
        this.code = code;
        this.type = type;
        this.operator = Objects.requireNonNull(operator);

    }

    // 비교자 생성 메서드
    private static final Comparator<Machine> COMPARATOR = Comparator.comparingInt((Machine m) -> m.code)
                                                            .thenComparing(m -> m.type)
                                                            .thenComparing(m -> m.operator);

    @Override
    public String toString() {
        return String.format("Code : %d\nType : %c\nOperator : %s",
                code, type, operator);
    }

    @Override
    public int compareTo(Machine m) {
        return COMPARATOR.compare(this, m);
    }
}

package com.lgm.study.chapter3.act2;

import java.util.Objects;

public class Machine {
    private final int code;
    private final char type;

    public Machine(int code, char type) {
        this.code = code;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Machine))
            return false;
        Machine m = (Machine) o;
        return (m.code == this.code && m.type == this.type);
    }

    // equals를 재정의할 시 hashCode도 재정의해야 함
    @Override
    public int hashCode() {
        return Objects.hash(code, type);
    }
}

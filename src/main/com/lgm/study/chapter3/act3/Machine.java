package com.lgm.study.chapter3.act3;

import java.util.Objects;

public class Machine {
    final int code;
    final char type;

    public Machine(int code, char type) {
        this.code = code;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof com.lgm.study.chapter3.act3.Machine))
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

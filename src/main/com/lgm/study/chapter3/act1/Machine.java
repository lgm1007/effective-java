package com.lgm.study.chapter3.act1;

public class Machine {
    private final int code;
    private final char type;

    public Machine(int code, char type) {
        this.code = code;
        this.type = type;
    }

    // equals 매개 변수를 Object로 꼭 받아야 Override
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Machine))
            return false;
        Machine m = (Machine) o;
        return (m.code == this.code && m.type == this.type);
    }
}

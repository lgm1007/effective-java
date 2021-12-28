package com.lgm.study.chapter3.act2;

import com.lgm.study.chapter3.act2.Machine;
import com.lgm.study.chapter3.act2.Operator;

import java.util.Objects;

public class FactoryMachine {
    private final Machine machine;
    private final Operator operator;

    public FactoryMachine(int code, char type, Operator operator) {
        machine = new Machine(code, type);
        this.operator = Objects.requireNonNull(operator);
    }

    // equals 매개 변수를 Object로 꼭 받아야 Override
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof FactoryMachine))
            return false;
        FactoryMachine fm = (FactoryMachine) o;

        // 컴포지션 사용 : 다른객체의 인스턴스를 자신의 인스턴스 변수로 포함하여 메서드 호출 (machine.equals())
        return (fm.machine.equals(machine) && fm.operator.equals(operator));
    }

    // equals를 재정의할 시 hashCode도 재정의해야 함
    @Override
    public int hashCode() {
        return Objects.hash(machine, operator);
    }
}

package com.lgm.study.chapter3.act2;

import org.junit.jupiter.api.Test;

public class MachineTest {
    @Test
    void machineTest() {
        Machine m1 = new Machine(1011, 'A');
        Machine m2 = new Machine(1012, 'B');
        Machine m3 = new Machine(1011, 'A');

        FactoryMachine fm1 = new FactoryMachine(1011, 'A', Operator.DANIAL);
        FactoryMachine fm2 = new FactoryMachine(1012, 'B', Operator.EVAN);
        FactoryMachine fm3 = new FactoryMachine(1011, 'A', Operator.FRANCISCO);
        FactoryMachine fm4 = new FactoryMachine(1011, 'A', Operator.DANIAL);

        // equals(Object) 가 같다고 판단하면 두 객체의 hashCode는 같은 값을 반환한다.
        System.out.println(m1.hashCode());  // 32367
        System.out.println(m2.hashCode());  // 32399
        System.out.println(m3.hashCode());  // 32367

        System.out.println(fm1);    // FactoryMachine@6ee31fe4
        System.out.println(fm2);    // FactoryMachine@1a76ca55
        System.out.println(fm3);    // FactoryMachine@15ed5e6e
        System.out.println(fm4);    // FactoryMachine@6ee31fe4
    }
}

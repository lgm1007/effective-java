package com.lgm.study.chapter3.act1;

import org.junit.jupiter.api.Test;

public class MachineTest {
    @Test
    void machineTest() {
        Machine m1 = new Machine(1001, 'A');
        Machine m2 = new Machine(1002, 'B');
        Machine m3 = new Machine(1001, 'A');
        FactoryMachine fm1 = new FactoryMachine(1001, 'A', Operator.ALEX);
        FactoryMachine fm2 = new FactoryMachine(1002, 'B', Operator.BENJAMIN);
        FactoryMachine fm3 = new FactoryMachine(1001, 'A', Operator.COVY);

        System.out.println(m1.equals(m3));      // TRUE
        System.out.println(m1.equals(fm1));     // FALSE
        System.out.println(m2.equals(fm2));     // FALSE
        System.out.println(fm1.equals(fm3));    // FALSE
    }
}

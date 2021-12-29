package com.lgm.study.chapter3.act3;

import org.junit.jupiter.api.Test;

public class FMtoStringTest {
    @Test
    void toStringTest() {
        FactoryMachine fm1 = new FactoryMachine(3001, 'C', Operator.GREGORY);
        FactoryMachine fm2 = new FactoryMachine(3002, 'D', Operator.ISAAC);

        // Code : 3001
        // Type : C
        // Operator : GREGORY
        System.out.println(fm1);

        // Code : 3002
        // Type : D
        // Operator : ISAAC
        System.out.println(fm2);
    }
}

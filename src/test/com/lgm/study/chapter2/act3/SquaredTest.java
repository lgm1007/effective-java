package com.lgm.study.chapter2.act3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquaredTest {
    @Test
    void squaredType1Test() {
        final int number = 10;
        final SquaredType1 st1 = SquaredType1.INSTANCE;

        Assertions.assertNotNull(st1);
        Assertions.assertInstanceOf(SquaredType1.class, st1);
        st1.square(number);
    }

    @Test
    void squaredType2Test() {
        final int number = 20;
        final SquaredType2 st2 = SquaredType2.getInstance();

        Assertions.assertNotNull(st2);
        Assertions.assertInstanceOf(SquaredType2.class, st2);
        st2.square(number);
    }

    @Test
    void squaredType3Test() {
        final int number = 30;
        SquaredEnum.INSTANCE.square(number);
    }
}

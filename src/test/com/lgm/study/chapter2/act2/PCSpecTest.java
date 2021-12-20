package com.lgm.study.chapter2.act2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PCSpecTest {
    @Test
    void type1BuildTest() {
        final String monQuality = "QHD";
        final String cpu = "Ryzen 7 3800X";
        final String gpu = "RTX 3080";
        final int ram = 32;

        // 빌더 패턴
        final PCSpecType1 pcSpecType1 = new PCSpecType1.Builder()
                                        .monQuality(monQuality)
                                        .cpu(cpu)
                                        .gpu(gpu)
                                        .ram(ram)
                                        .build();

        Assertions.assertNotNull(pcSpecType1);
        Assertions.assertInstanceOf(PCSpecType1.class, pcSpecType1);
    }

    @Test
    void type2BuildTest() {
        final int id = 2112201956;
        final String monQuality = "2K";
        final String cpu = "Intel i9-9980";
        final String gpu = "RTX 3080";
        final int ram = 16;

        final PCSpecType2 pcSpecType2 = new PCSpecType2.Builder(id)
                                        .monQuality(monQuality)
                                        .cpu(cpu)
                                        .gpu(gpu)
                                        .ram(ram)
                                        .build();

        Assertions.assertNotNull(pcSpecType2);
        Assertions.assertInstanceOf(PCSpecType2.class, pcSpecType2);
    }
}

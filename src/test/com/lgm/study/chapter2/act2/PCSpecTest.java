package com.lgm.study.chapter2.act2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PCSpecTest {
    @Test
    void buildTest() {
        final String monQuality = "QHD";
        final String cpu = "Ryzen 7 3800X";
        final String gpu = "RTX 3080";
        final int ram = 32;

        final PCSpec pcSpec = new PCSpec.Builder()
                                        .monQuality(monQuality)
                                        .cpu(cpu)
                                        .gpu(gpu)
                                        .ram(ram)
                                        .build();

        Assertions.assertNotNull(pcSpec);
        Assertions.assertInstanceOf(PCSpec.class, pcSpec);
    }
}

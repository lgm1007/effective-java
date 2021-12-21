package com.lgm.study.chapter2.act5;

import org.junit.jupiter.api.Test;

public class DefectTesterTest {
    @Test
    void screwDITest() {
        ScrewFunc screwFunc = new ScrewFunc();
        DefectTester tester = new DefectTester(screwFunc);
        tester.runTest();
    }

    @Test
    void pressDITest() {
        PressFunc pressFunc = new PressFunc();
        DefectTester tester = new DefectTester(pressFunc);
        tester.runTest();
    }
}

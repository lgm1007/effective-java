package com.lgm.study.chapter3.act5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CompareToTest {
    @Test
    void compareToTest() {
        Machine am = new Machine(5000, 'A', Operator.JAYCE);
        Machine bm = new Machine(5000, 'B', Operator.JAYCE);
        Machine cm = new Machine(5001, 'C', Operator.LOGAN);
        Machine dm = new Machine(5000, 'A', Operator.KAVIN);
        Machine em = new Machine(5001, 'B', Operator.LOGAN);

        List<Machine> machineList = new ArrayList<>();
        machineList.add(am);
        machineList.add(bm);
        machineList.add(cm);
        machineList.add(dm);
        machineList.add(em);

        // Collections.sort() 정렬 기준 => compareTo() 메서드
        Collections.sort(machineList);
        machineList.stream().collect(Collectors.toList())
                .forEach(s -> System.out.println(s));

        // 출력 결과
        // Code : 5000
        // Type : A
        // Operator : JAYCE
        // Code : 5000
        // Type : A
        // Operator : KAVIN
        // Code : 5000
        // Type : B
        // Operator : JAYCE
        // Code : 5001
        // Type : B
        // Operator : LOGAN
        // Code : 5001
        // Type : C
        // Operator : LOGAN
    }
}

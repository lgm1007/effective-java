package com.lgm.study.chapter5.act5;

import org.junit.jupiter.api.Test;

import java.util.Set;

public class GenericMethodTest {
    @Test
    void testGenericMethod() {
        GenericMethodClass genericMethodClass = new GenericMethodClass();
        Set<String> set1 = Set.of("알파", "베타", "감마");
        Set<String> set2 = Set.of("델타", "엡실론", "제타");
        Set<Integer> setInt1 = Set.of(1, 2, 3);
        Set<Integer> setInt2 = Set.of(4, 5, 6);

        Set<String> result1 = GenericMethodClass.union(set1, set2);     // result1 : [엡실론, 베타, 알파, 감마, 제타, 델타]
//        Set<String> result2 = GenericMethodClass.union(set1, setInt);   // error: cannot be applied to Set<String>, Set<Integer>
        Set<Integer> result2 = GenericMethodClass.union(setInt1, setInt2);  // result2 : [1, 2, 3, 4, 5, 6]
        System.out.println(result1);
        System.out.println(result2);
    }
}

# 박싱된 기본 타입보다는 기본 타입을 사용하라
### 자바의 두 가지 타입
1. 기초 자료형 (primitive types)
    * int, double, boolean
2. 레퍼런스 타입 (reference types)
    * List, Set 등
### 기초 자료형의 오토박싱과 오토언박싱
* 기초 자료형과 박싱된 기초 자료형의 주요 차이점
    1. 기초자료형은 값만을 가질 수 있는 반면, 박싱된 타입은 값 자체와는 구별되는 식별성을 가짐
    2. 기초자료형은 완전한 함수적 값이지만, 박싱된 타입은 함수적이지 않은, null을 취급할 수 있음
    3. 기초자료형은 박싱된 타입보다 시간 효율성과 공간 효율성이 높음
### 언제 박싱 타입을 사용하면 안되는가?
```java
Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1: (i == j ? 0 : 1)

// ...

naturalOrder.compare(new Integer(42), new Integer(42))    // 결과는 0을 기대하지만, 실제로는 1
```
* 위 코드의 문제는 `==`
  * `==` 연산은 레퍼런스 타입일 경우 동일성(identity)를 판단함
  * 즉 42라는 값에 `==`연산을 하는 것이 아니라 레퍼런스 동일성을 판단하는 것
  * **박싱 타입에 `==`연산을 사용하는 것은 항상 잘못**
* 위 문제는 박싱 타입을 언박싱하여 비교하면 해결
```java
Comparator<Integer> naturalOrder = (boxedI ,boxedJ) -> {
	int i = boxedI, j = boxedJ;
	return (i < j) ? -1 : (i == j) ? 0 : -1;
};
```
### 언제 박싱 타입을 사용해도 되는가?
* 컬렉션의 항목/키(key)/값(value) 에 사용
* 파라미터화 타입(parameterized type)에는 기초 자료형을 사용할 수 없음
   * `ThreadLocal<int>`는 불가능하고 `ThreadLocal<Integer>`로 작성해야 한다는 의미
### 결론
* 박싱된 타입은 값 타입이 아닌 레퍼런스 타입에 속한다.
* 박싱 타입은 `==` 연산 시 동일성(identity)이 계산된다.
* 박싱 타입은 연산이 매우 느리다.
* 박싱 타입과 기초 자료형을 섞어 사용하면 안 된다. (Null 관련 Exception 발생 위험)
---
[[Prev act >>]](../act4/README.md)  
[[Next act >>]](../act6/README.md)
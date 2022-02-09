# 명명 패턴보다 어노테이션을 사용하라
* 명명 패턴 예시 : 테스트 프레임워크인 JUnit은 버전 3까지 테스트 메서드 이름을 test로 시작하게끔 함
### 명명 패턴의 단점
1. 오타가 나면 안 됨
   * 실수로 testSafe~~ 라고 지으면 JUnit 3은 해당 메서드를 지나치기에 테스트 통과했다고 생각할 수 있음
2. 올바른 프로그램 요소에서만 사용되리라는 보장이 없음
3. 프로그램 요소를 매개변수로 전달할 마땅한 방법이 없음
   * 만약 특정 예외를 던져야만 성공하는 테스트가 있다고 가정할 시
     * 기대하는 예외 타입을 테스트의 매개변수로 전달해야하는 상황
     * 이때 구분 방법으로, 예외의 이름을 테스트 메서드 이름에 덧붙이는 방법이 있음
     * 하지만 보기도 안좋고 깨지기 쉬움
     * 또한, 컴파일러는 메서드 이름에 덧붙인 문자열이 예외를 가리키는지 알 수 없음
* 위 단점들을 어노테이션이 해결해줌
  * 어노테이션은 JUnit 버전 4부터 전면 도입
### 테스트 프레임워크를 사용한 어노테이션 동작 방식
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    ...
}
```
* `@Retention`과 `@Target`의 메타 어노테이션을 사용해 어노테이션 생성
  * `@Retention(RetentionPolicy.RUNTIME)` : 런타임까지 유용한 어노테이션
  * `@Target(ElementType.METHOD)` : 해당 어노테이션은 메서드 선언에서만 사용
### 마커 어노테이션
* `아무 매개변수 없이 단순히 대상에 마킹한다.`는 뜻의 어노테이션
* 해당 어노테이션을 사용하면 `Test 이름에 오타를 내거나 메서드 선언 외의 프로그램 요소에 달면 컴파일 오류`를 냄
```java
public class Sample {

    @Test
    public static void m1() {}    //성공해야 한다.

    public static void m2() {}

    @Test
    public static void m3() {        //실패해야 한다.
        throw new RuntimeException("실패");
    }

    public static void m4() {}

    @Test
    public void m5() {}             //잘못 사용했다. 정적 메소드가 아니다.

    public static void m6() {}

    @Test
    public static void m7() {        //실패해야 한다.
        throw new RuntimeException("실패");
    }

    public static void m8() {}

}
```
* `@Test` 가 달리지 않은 메서드는 테스트 도구가 무시할 것
* `@Test`의 사용 목적 : Sample 클래스의 의미에는 직접적인 영향을 주지 않고, `@Test`에 관심 있는 도구에서 특별한 처리를 할 기회를 줌
### 특별한 예외를 던져야 성공하는 테스트
* Test 진행할 객체 설정
```java
public class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public static void m1() {   //성공해야 한다 (0으로 나눔).
        int i = 0;
        i = i/i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m2() {   //실패해야 한다. (다른 예외 발생)
        int [] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m3() {   //실패해야 한다. (예외가 발생하지 않음)

    }
}
```
* `@Test`와의 가장 큰 차이는 어노테이션 매개변수의 값을 추출하여 테스트 메서드가 올바른 예외를 던지는지 확인한다는 점
### 예외를 여러 개 명시하고 그 중 하나가 발생하면 성공인 테스트
* 커스텀 어노테이션 설정
```java
public @interface ExceptionTest {
    Class<? extends Throwable>[] value();   //예외를 여러개 명시
}
```
* Test 진행 객체 설정
```java
public class Sample2 {
    ...

    @ExceptionTest(ArithmeticException.class)
    public static void m3() {   //실패해야 한다. (예외가 발생하지 않음)

    }

    //예외 두개중 하나만 걸려도 true
    @ExceptionTest({IndexOutOfBoundsException.class, NullPointerException.class})
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }
}
```
### 결론
* 어노테이션으로 할 수 있는 일을 명명 패턴으로 처리할 필요 없다.
* 일반 프로그래머가 어노테이션 타입을 정의할 일은 거의 없다.
  * 자바 프로그래머라면 예외 없이 자바가 제공하는 어노테이션은 사용해야 한다.
---
[[Prev act >>]](../act5/README.md)  
[[Next act >>]](../act7/README.md)
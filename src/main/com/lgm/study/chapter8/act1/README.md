# 매개변수가 유효한지 검사하라
### 매개변수 검사
* 매개변수 겸사를 제대로 하지 않을 경우 문제 발생 가능
* 메서드가 수행되는 중간에 모호한 예외를 던지며 실패할 수 있음
    * 메서드는 정상적으로 수행되었는데 잘못된 결과를 반환하는 경우
    * 매개변수 검사에 실패하면 실패 원자성(failure atomicity)을 어기는 결과 발생
* public과 protected 메서드는 매개변수 값이 잘못됐을 때 던지는 예외를 문서화해야 함
  * `@throws` 자바독 태그 사용
    * 매개변수 제약을 문서화하면 그 제약을 어길 경우 발생하는 예외도 기술해야 함
```java
public class Calculator {
    /**
     * (현재 값 mod m) 값을 반환한다. 이 메서드는
     * 항상 음이 아닌 BigInteger를 반환한다는 점에서 remainder 메서드와 다르다.
     * @param m 계수(양수여야 한다)
     * @return 현재 값 mod m
     * @throws ArithmeticException m이 0보다 작거나 같으면 발생한다.
     */
    public BigInteger mod(BigInteger m) {
        if (m.signum() <= 0) {
            throw new ArithmeticException("계수(m)는 양수여야 합니다. " + m);
        }
        
        // 동작 수행 ...
        
        return null; // 편의상 null을 리턴했다.
    }
}
```
#### 자바의 null 검사 기능 사용하기
```java
public class NullCheck {

    private String strategy;

    public void changeStrategy(String strategy) {
        this.strategy = Objects.requireNonNull(strategy, "전략");
    }
}
```
* 반환값은 무시하고 필요한 곳 어디든 순수한 null 검사 목적으로 사용 가능함
### 단언문(assert)을 사용한 매개변수 유효성 검증
* 공개되지 않은 메서드라면 메서드가 호출되는 상황 통제 가능
  * public이 아닌 메서드라면 단언문(assert)을 사용해 매개변수 유효성 검증 가능
```java
public class AssertTest {

    private static void sort(long a[], int offset, int length) {
        assert a != null;
        assert offset >= 0 && offset <= a.length;
        assert length >= 0 && length <= a.length - offset;
        
        // 계산 수행 ...
    }
}
```
* 위 단언문들은 자신이 단언한 조건이 무조건 참이라고 선언
  * 참이 아닐경우 AssertionError 발생
* 단언문은 일반적인 유효성 검사와는 다름
   1. 실패하면 AssertionError을 던짐
   2. 런타임에 아무런 효과, 아무런 성능 저하가 없음
### 나중에 쓰기 위해 저장하는 매개변수의 유효성을 검사하라
* 생성자 : **나중에 사용하려 저장하는 매개변수의 유효성을 검사하라**는 원칙의 특수한 사례
  * 생성자 매개변수의 유효성 검사는 클래스 불변식을 어기는 객체가 만들어지지 않게 하는데 꼭 필요
* **메서드 몸체 실행 전에 매개변수 유효성을 검사해야 한다**는 규칙에도 예외는 있음
  * 유효성 검사 비용이 지나치게 높거나 상용적이지 않을 때, 또는 계산 과정에서 암묵적으로 검사가 수행될 때
### API 문서에 정의된 예외와 다른 예외 발생 경우
* 계산 중 잘못된 매개변수 값을 사용해 발생한 예외가 API 문서에도 던지기로 한 예외와 다를 수 있음
  * 예외 번역(exception translate, 또는 예외 전환) 관용구를 사용해 API 문서에 기재된 예외로 번역해야 함
### 결론
* 매개변수에 제약을 두는 게 좋다고 해석해선 안 된다.
  * 메서드는 최대한 범용적으로 설계해야 한다.
  * 메서드가 건네받은 값으로 제대로 동작할 수 있다면, 매개변수 제약은 적을수록 좋다.
* 메서드나 생성자를 작성할 때면 그 매개변수들에 어떤 제약이 있을지 생각해야 하며, 그 제약들을 문서화하고 메서드 코드 시작 부분에서 명시적으로 검사해야 한다.
---
[[Prev act >>]](../../chapter7/act7/README.md)  
[[Next act >>]](../act2/README.md)
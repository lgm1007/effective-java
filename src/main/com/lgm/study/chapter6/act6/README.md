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
---
[[Prev act >>]](../act5/README.md)  
[[Next act >>]](../act7/README.md)
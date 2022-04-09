# 필요 없는 검사 예외 사용은 피하라
### 검사 예외 회피 방법 1) 비검사 예외
```java
} catch (TheCheckedException e) {
	throw new AssertionError();
}
```
```java
} catch (TheCheckedException e) {
	e.printStackTrace();
	System.exit(1);
}
```
* 첫 번째 코드 : 프로그래머가 비검사 예외를 호출하는 것으로 해결
* 두 번째 코드 : 에러 스택 코드를 호출하고 시스템 종료
* 두 경우 모두 **애초에 비검사 예외로 API를 만들면** 해결될 문제
### 검사 예외 회피 방법 2) Optional
* 검사 예외를 던지는 대신 단순히 빈 Optional을 반환하면 됨
* 단점 : 예외가 발생한 이유를 알려주는 부가정보를 담을 수 없음
### 검사 예외 회피 방법 3) 메서드 쪼개기
* 검사 예외를 던지는 메서드를 2개로 쪼개 비검사 예외로 바꾸는 것
  * 쪼개진 첫 번째 메서드는 예외가 던저질지 여부를 boolean 값으로 반환
  * **메서드 쪼개기 전**
  ```java
  try {
    Obj.action(args);
  } catch (TheCheckException e) {
    // ...
  }
  ```
  * **메서드 쪼개기 후**
  ```java
  if (obj.actionPermitted(args)) {
    obj.action(args);
  } else {
    // ...
  }
  ```
  * action 내의 로직을 2개의 메서드(`actionPermitted`, `action`)으로 나눈 모습
  * 깔끔함을 포기한 대신 유연함을 얻음
    * 단, `actionPermitted`는 상태 검사 메서드에 해당하므로 주의해야 함,
      * 외부 동기화 없이 여러 스레드가 동시에 접근할 수 있거나 외부 요인에 의해 상태가 변할 수 있다면 해당 방법은 좋지 않음
### 결론
* 꼭 필요한 곳에서만 사용한다면 검사 예외는 프로그램의 안전성을 높여주지만, 남용하면 오히려 쓰기 힘든 API를 만든다.
* API 호출자가 예외 상황에서 복구할 방법이 없다면 비검사 예외를 던지자.
* 복구가 가능하고 호출자가 그 처리를 해주길 바란다면, Optional 사용을 검토하라.
---
[[Prev act >>]](../act2/README.md)  
[[Next act >>]](../act4/README.md)
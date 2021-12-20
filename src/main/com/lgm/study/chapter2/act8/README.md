# Finalizer와 Cleaner 사용을 피하라
### 자바에서 객체 소멸은 GC가, 비메모리 자원회수는 `try-with-resource`, `try-finally`로 해결함
### 자바에서 제공하는 두 가지 객체 소멸자인 `finalizer`와 `cleaner`는 기본적으로 사용하지 않아야 함
* 즉시 수행된다는 보장이 없다
* 자원 회수가 제멋대로 지연된다
* 수행 여부조차 보장하지 않는다
* 동작 중 발생한 예외가 무시된다
* GC의 효율을 떨어뜨린다
* 보안 문제를 일으킬 수 있다
### 정상적으로 자원 반납하기
* `AutoCloseable`
* `try-finally` : 명시적으로 자원 반납
* `try-with-resource` : 암묵적으로 자원 반납, 가장 이상적인 방법
### Finalizer와 Cleaner의 사용처
* `AutoCloseable`을 구현하지 않았을 경우를 대비한 안전망 역할
* 네이티브 피어와 연결된 객체
  * 네이티브 피어 : 일반 자바 객체가 네이티브 메서드를 통해 기능을 위임한 네이티브 객체

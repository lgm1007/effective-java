# 표준 예외를 사용하라
### 자바 라이브러리는 대부분 API에서 사용하기 좋은 예외를 제공한다
* 코드 재사용 측면에서 좋음
* 자신의 API를 다른 사람이 사용하기 좋음
  * 이미 익숙한 규약을 따르기 때문
* 예외 클래스가 적을수록 메모리 사용량도 줄어듬
### 주로 사용하는 예외들
* `IllegalArgumentException`
  * 인수로 부적절한 값을 넘길 때
  * 예) 반복횟수에 음수를 건넬 경우
* `IllegalStateException`
  * 대상 객체의 상태가 메서드를 수행하는데 적합하지 않은 경우
  * 예) 초기화되지 않은 객체를 사용한 경우
* `NullPointerException`
  * Null을 허용하지 않은 메서드에 null을 건넬 경우
* `ConcurrentModificationException`
  * 단일 스레드에서 사용하라고 만든 객체를 여러 스레드에서 동시에 수정하려고 한 경우
  * 예) Iterator가 동작중인 Collection을 수정하려 할 때
* `UnsupportedOperationException`
  * 객체차 클라이언트가 요청한 동작을 지원하지 않을 때
  * 예) 원소를 넣을 수 있는 List 구현체 list에서 누군가 remove 메서드 호출
* 가능하다면 더 특수한 에외를 따로 구분하여 사용하라
### 주의 사항
* Exception, RuntimeException, Throwable, Error를 직접 재사용하지 마라
  * 추상 클래스라고 생각할 것 (여러 예외 성격을 포함하는 클래스)
* 예외는 직렬화 가능
* 인수값이 무엇이었든 어차피 실패했을 경우 `IllegalStateException`
* 인수값이 중요했다면 `IllegalArgumentException`
### 결론
* 상황에 부합한다면 표준 예외를 사용하라.
* 더 많은 정보를 제공하기를 원한다면 예외를 확장해도 좋지만, 예외는 직렬화 가능하다는 사실을 기억하라.
---
[[Prev act >>]](../act3/README.md)  
[[Next act >>]](../act5/README.md)
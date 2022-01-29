# 인터페이스는 구현하는 쪽을 생각해 설계하자
* 인터페이스에 디폴트 메서드를 선언하면, 그 인터페이스를 구현한 클래스에서 디폴트 메서드를 재정의하지 않았을 경우 디폴트 구현이 사용됨
  * 기존 인터페이스에 메서드를 추가하는 길이 열림
### Collection 의 디폴트 메서드 removeIf
* removeIf 자바 8에서 Collection 인터페이스에 새롭게 추가된 메서드
* 주어진 boolean 함수가 true를 반환하는 모든 원소를 제거
```java
default boolean removeIf(Predicate<? super E> filter) {
    Objects.requireNonNull(filter);
    boolean removed = false;
    final Iterator<E> each = iterator();
    while(each.hasNext()) {
        if(filter.test(each.next())) {
            each.remove();
            removed = true;
        }
    }
    return removed;
}
```
* Collection에 새롭게 추가된 디폴트 메서드는 모든 Collection에 대해 정상적으로 작동할까?
### apache 가 만든 SynchronizedCollection
* 해당 클래스는 스레드 안정성을 위한 동기화가 되어있는 list의 wrapper 클래스
* 동기화 코드 존재
```java
@Override
public boolean add(final E object) {
    synchronized (lock) {
        return decorated().add(object);
    }
}
```
* 해당 클래스는 removeIf 메서드를 재정의하고 있지 않아, 자바8과 함께 사용하면 스레드 안정성을 갖지 못함
  * SynchronizedCollection 인스턴스를 여러 스레드가 공유하는 환경에서 한 스레드가 removeIf 메서드를 호출하면 `ConcurrentModificationException` 발생하거나 예기치 못한 결과로 이루어짐
* 따라서 JDK에서 문제 예방을 위해 removeIf 디폴트 메서드 재정의
### Collections.synchronizedCollection 클래스의 removeIf()
```java
@Override
public boolean removeIf(Predict<? super E> filter) {
    synchronized (mutex) {return c.removeIf(filter);}
}
```
* 하지만, JDK에 속하지 않은 제 3의 기존 Collection 구현체들은 수정될 기회가 없었음
  * 일부는 여전히 수정되고 있지 않음
### So What?
* 기존 인터페이스에 디폴트 메서드로 새 메서드를 추가하는 일은 꼭 필요한 경우가 아니라면 피해야 함
* 디폴트 메서드로 기존 인터페이스에 새로운 메서드를 추가하면 큰 위험도 딸려옴
* 새로운 인터페이스를 만드는 경우라면 디폴트 메서드 구현을 제공하는 것은 아주 유용하며 더 쉽게 활용할 수 있게 함
  * 새로운 인터페이스는 반드시 테스트 필요
  * 인터페이스 릴리즈 후에라도 결함을 수정하는 게 가능한 경우도 있지만, 절대 그 가능성에 기대하면 안됨
---
[[Prev act >>]](../act6/README.md)  
[[Next act >>]](../act8/README.md)
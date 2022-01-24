# 한정적 와일드카드를 사용해 API 유연성을 높여라
### 매개변수화 타입은 불공변(invariant)
* 서로 다른 타입 Type1과 Type2 가 있을 때, `List<Type1>`은 `List<Type2>`의 하위 타입도 상위 타입도 아님
* 즉, `List<String>`은 `List<Object>`의 하위 타입이 아님
### 와일드카드 타입을 사용하지 않은 메서드 - 결함 있음
```java
public class Stack {
    public void pushAll(Iterable<E> src) {
        for(E e : src) {
            push(e);
        }
    }
}
```
* 위 메서드는 `Iterable src`의 원소 타입이 스택의 원소 타입과 일치하면 잘 작동함
* 하지만 `Stack<Number>`로 선언 후 pushAll(`IntegerType`)을 호출하면 동작하지 않음
* 매개변수화 타입은 불공변이기 때문
### E 생성자(producer) 매개변수에 와일드카드 타입 적용
```java
public class Stack {
    public void pushAll(Iterable<? extends E> src) {
        for(E e : src) {
            push(e);
        }
    }
}
```
* 자바에선 한정적 와일드카드 타입이라는 매개변수화 타입 지원
* 'E의 Iterable'이 아니라 'E의 하위 타입의 Iterable'이라는 뜻
* 하위 타입은 자기 자신도 포함
* 생산자(producer) : 입력 매개변수로부터 이 컬렉션으로 원소를 옮겨 담는다는 뜻
### 와일드카드 타입을 사용하지 않은 메서드2 - 결함 있음

```java
public class Stack {
    public void popAll(Collection<E> dst) {
        while(!isEmpty()) {
            dst.add(pop());
        }
    }
}
```
* 타입이 다를 경우, "`Collection<Object>`는 `Collection<Number>`의 하위 타입이 아니다." 라는 오류 발생
### E 소비자(consumer) 매개변수에 와일드카드 타입 적용
```java
public class Stack {
    public void popAll(Collection<? super E> dst) {
        while(!isEmpty()) {
            dst.add(pop());
        }
    }
}
```
* 해결: popAll 메서드의 입력 매개변수의 타입이 'E의 Collection'이 아니라 'E의 상위 타입의 Collection'이어야 함
* 소비자(consumer) : 이 컬렉션 인스턴스의 원소를 입력 매개변수로 옮겨 담을 경우 해당 매개변수를 의미
### 펙스 (PECS) : producer-extends, consumer-super
* 와일드카드 타입 사용 기본 원칙 = 매개변수화 타입 T가 생산자라면 `<? extends T>`, 소비자라면 `<? super T>`를 사용
### Comparable은 언제나 소비자이다
* 따라서 `Comparable<E>` 보다는 `Comparable<? super E>`를 사용하는 게 더 나음
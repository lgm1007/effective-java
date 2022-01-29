# 이왕이면 제네릭 타입으로 만들어라
### 제네릭 타입이어야 마땅한 클래스 - Object 기반 Stack
```java
public class Stack {
    private Object[] elements;
    ...
    
    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    
    public Object pop() {
        if (size == 0) throw new EmptyStackException();
        Object result = elements[-size];
        elements[size] = null;
        return result;
    }
    
    ...
}
```
* 해당 클래스는 제네릭 타입이어야 마땅함
    * 스택에서 꺼낸 객체를 형변환해야 하는데, 이 때 런타임 오류가 발생할 수 있음
* 일반 클래스를 제네릭 클래스로 만드는 방법, 클래스 선언에 타입 매개변수 추가하기
    * 스택이 담을 원소의 타입 하나만 추가
    * 보통 타입 이름으로 `E` 사용
    * `E`와 같은 실체화 불가 타입으로 배열을 만들 수 없음
```java
// Object -> E
public class Stack<E> {
    // Object[] -> E[]
    private E[] elements;
    ...
  
    public Stack() {
        elements = new E[DEFAULT_INITIAL_CAPACITY];
    }
    
    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }
    
    public E pop() {
        ...
        E result = elements[-size];
        elements[size] = null;
        return result;
    }
}
```
### 배열을 사용하는 코드를 제네릭으로 만들 때 문제 발생 해결책
1. 제네릭 배열 생성을 금지하는 제약을 우회하기
   * Object 배열을 생성한 다음 제네릭 배열로 형변환
   * 컴파일러는 오류 대신 경고 보냄
   * 타입 안전하지 않다는 경고
   * 비검사 형변환이 프로그램의 타입 안전성을 해치지 않음을 확인
   * 형변환이 안전함을 스스로 증명했으면 범위를 최소로 하여 `@SuppressWarnings` 어노테이션으로 해당 경고 숨김
      * 위 코드의 경우 생성자가 비검사 배열 생성 말고는 다른 일이 없으니 생성자 전체에서 경고를 숨겨도 괜찮음
```java
    ...
    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }
```
2. elements 필드의 타입을 `E[]` -> `Object[]` 로 바꾸기
   * 다른 오류가 발생함
   * 배열이 반환한 원소를 `E`로 형변환하면 오류 대신 경고가 뜸
   * `E`는 실체화 불가 타입이므로 컴파일러는 런타임에 이뤄지는 형변환이 안전한지 증명할 방법이 없음
   * pop 메서드 전체에서 경고를 숨기지 말고 비검사 형변환을 수행하는 할당문에서만 숨김
```java
    private Object[] elements;
    ...
    public E pop() {
        if (size == 0) throw new EmptyStackException();
        
        // push에서만 E타입을 허용하므로 해당 형변환은 괜찮음
        @SuppressWarnings("unchecked")
        E result = (E) elements[--size];
        
        elements[size] = null;
        return result;
    }
```
* 첫 번째 방법은 가독성이 더 좋음
    * 배열의 타입을 `E[]`로 선언하여 오직 `E` 인스턴스만 받음을 어필
    * 형변환을 배열 생성 시 단 한번만 수행
* 두 번째 방법은 배열에서 원소를 읽을 때마다 형변환 수행
    * 현업에서는 첫 번째 방식을 더 선호하며 자주 사용
    * 하지만, 배열의 런타임 타입이 컴파일 타임 타입과 달라 힙 오염(heap pollution)을 일으킴
    * 힙 오염 때문에 두 번째 방식을 고수하기도 함
### 타입 매개변수
* 대다수의 제네릭 타입은 매개변수에 아무런 제약을 두지 않음
    * ex) `Stack<Object>`, `Stack<int[]>`, `Stack<List<String>>` 등
* 단, 기본 타입은 사용할 수 없음
    * ex) `Stack<int>`, `Stack<double>` 등은 컴파일 오류 발생
### 한정적 타입 매개변수
* 타입 매개변수에 제약을 두는 제네릭 타입도 존재함
    * java.util.concurrent.DelayQueue
    ```java
    public class DelayQueue<E extends Delayed> extends AbstractQueue<E>
    ```
    * 이는 타입 매개변수로 Delayed의 하위 타입만 받는다는 뜻
    * DelayQueue 자신과 DelayQueue를 사용하는 클라이언트는 DelayQueue 원소에서 형변환 없이 바로 Delayed 클래스의 메서드를 호출할 수 있음
### 결론
* 클라이언트에서 직접 형변환해야 하는 타입보다 제네릭 타입이 더 안전하고 쓰기 편함
* 새로운 타입을 설계할 땐 형변환 없이도 사용할 수 있도록 설계하라
    * 이를 위해 제네릭 타입으로 만들어야 하는 경우가 있음
    * 제네릭 타입 변경은 기존 클라이언트에는 아무 영향을 주지 않으면서, 새로운 사용자를 편하게 해주는 방법
---
[[Prev act >>]](../act3/README.md)  
[[Next act >>]](../act5/README.md)
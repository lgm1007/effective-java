# 다 쓴 객체 참조를 해제하라
### 자바에는 GC가 있기 때문에 GC가 알아서 객체를 회수해간다고 해서 메모리 관리에 신경쓰지 않아도 된다는 것은 오해이다
### 메모리 누수 방지 = GC의 소멸 대상으로 지정하기
* 직접 할당 해제
  * 객체 참조 변수를 null로 초기화
  * 보통 예외적인 상황에서나 함
  * 클래스 내에서 메모리를 관리하는 객체(ex. Stack)이면, 이 방법이 옳음

```java
public class Stack {
  private Object[] elements;
  private int size = 0;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  public Stack() {
    this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
  }

  // 메모리 누수 발생 : Stack에서 꺼낸 객체가 여전히 배열에서 참조하고 있음
  public Object pop() {
    if (size == 0) throw new EmptyStackException();
    return elements[--size];
  }
}
```
```java
// 참조 해제하기 : 해당 참조에 대하여 null값 대입
public Object pop() {
    if (size == 0) throw new EmptyStackException();
    
    Object result = element[--size];
    element[size] = null;
    return result;
}
```
* Scope를 통한 자동 해제
  * 보통 변수에 대한 Scope가 종료되면 참조가 해제되어 GC 대상이 됨
  * `try-catch`문의 경우 `catch` 내에서 사용한 변수를 `try` 내에서 참조하지 못하므로 `finally` 구문에서 참조 해제함
### 캐시 메모리 누수 주의
* 객체 사용 후 참조 해제를 잊어버리는 경우
* 캐시 외부에서 참조하는 동안만 필요하다면 `WeakHashMap`을 사용하여 캐시 만드는 것 추천

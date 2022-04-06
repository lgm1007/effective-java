# 예외는 진짜 예외 상황에만 사용하라
* 절대 **일상적인 제어 흐름용**으로 사용해선 안 됨
* 예외를 잘못 사용한 예
```java
try {
    int i = 0;
    while(true) {
        range[i++].climb();
    }
} catch(ArrayIndexOutOfBoundsException e) {
	
}
```
* 무한루프를 돌다 배열의 끝에 도달하면 `ArrayIndexOutOfBoundsException` 이 발생하면 끝내버리는 코드
* **예외를 사용한 쪽이 표준 관용구보다 훨씬 느림**
```java
// 표준 관용구
for(Mountain m : range) {
    m.climb();
}
```
### 예외 관점에서 잘 설계된 API
* 잘 설계된 API는 클라이언트가 정상적인 제어 흐름에서 예외를 사용할 일이 없게 해야 함
* `상태 의존적 메서드`를 제공하는 클래스는 `상태 검사 메서드`도 함께 제공해야 함
  * 예) Iterator 인터페이스의 next(상태 의존적 메서드)와 hasNext(상태 검사 메서드)를 제공
    ```java
    // Iterator 표준 관용구 사용
    for(Iterator<Foo> i = collection.iterator(); i.hasNext();) {
        Foo foo = i.next(); 
        // ...
    }
    
    // Iterator가 hasNext를 제공하지 않았을 경우...
    try{
        Iterator<Foo> i = collection.iterator();
        while(true) {
            Foo foo = i.next();
            // ...
        }
    } catch(NoSuchElementException e) {
    }
    ```
  * 반복문에 예외를 사용하면 장황하고 햇갈리며, 속도도 느리고 엉뚱한 곳에서 발생한 버그를 숨기기도 함
### 결론
* 예외는 예외 상황에서 쓸 의도로 설계되었다.
* 정상적인 제어 흐름에서는 사용해선 안 되며, 이를 프로그래머에게 강요하는 API를 만들어서도 안 된다.
---
[[Prev act >>]](../../chapter9/act12/README.md)  
[[Next act >>]](../act2/README.md)
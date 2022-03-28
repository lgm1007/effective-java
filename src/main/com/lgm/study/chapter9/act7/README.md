# 문자열 연결은 느리니 주의하라
* 문자열 연결 연산자(+)는 편하지만 사용하기 시작하면 성능 저하를 감내하기 어려움
* 문자열 연결 연산자로 문자열 n개를 잇는 시간은 `n^2`배에 비례함
* 문자열 연결을 잘못 사용한 예 - 느림
```java
public String statement() {
    String result = "";
    for (int i=0; i<numItems(); i++){
      //문자열 연결
      result += lineForItem(i);
    }
    return result;
}
```
* 성능을 포기하고 싶지 않다면 **StringBuilder**를 사용하라
  * StringBuilder를 사용하면 성능이 크게 개선될 것
```java
public String statementWithBuilder() {
    StringBuilder b = new StringBuilder(numItems() * LINE_WIDTH);
    for ( int i = 0; i < numItems(); i+=)
        b.append(lineForItem(i));
    return b.toString();
}
```
* 위는 품목 100개의 `lineForItem`이 길이 80인 문자열을 반환할 때, 약 6.5배 차이남
* 이외에도 `문자 배열`을 사용하거나 문자열을 연결하지 않고 처리하는 방법이 있음
### 결론
* 성능에 신경써야 한다면 많은 문자열을 연결할 때는 문자열 연결 연산자(+)는 피하자.
* 문자열 연결 연산자 대신 **StringBuilder**의 append 메서드를 사용하라.
* 문자 배열을 사용하거나 문자열 연결을 하지 않고 하나씩 처리하는 방법도 있다.
---
[[Prev act >>]](../act6/README.md)  
[[Next act >>]](../act8/README.md)
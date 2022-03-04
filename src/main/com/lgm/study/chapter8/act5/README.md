# 가변인수는 신중히 사용하라
### 가변인수 (varargs)
* 가변인수는 명시된 타입의 인수를 0개 이상 받기 위해 사용
* 가변인수 메서드를 호출하면, 호출 시 **인수의 갯수만큼의 사이즈를 가진 배열**을 만들고, 이를 배열에 저장하여 메서드에 건네줌
* 가변인수 사용 예시 - 건네진 인수의 평균
```java
public int average(int... args) {
    int sum = 0;
    int size = args.length;
    for (int arg : args) {
        sum += arg;
    }
    return sum / size;
}
```
### 사용 1. 필수 인수를 지정하라
* 위 `average()` 메서드에는 한가지 문제가 있음
  * 인수의 갯수가 0일 때 분모가 0이 되어 런타임 에러 발생 가능
  * 위 에러 방지용 코드
  ```java
  if(args.length == 0) throw new IllegalArgumentException();
  ```
  * 하지만, 코드가 지저분해진다는 단점
  * 이는 가변인수 외에 **필수 인수를 지정하는 방법**으로 해결 가능
  ```java
  public int average(int first, int... args) {
      int sum = first;
      int size = args.length + 1;   // 1 더해줌 (first 인수)
      for (int arg : args) {
          sum += arg;
      }
      return sum / size;
  }
  ```
  * 필수 인수를 지정해서 메서드에 전달되는 인수의 개수가 0개가 되는 것을 방지
### 사용 2. 성능에 민감한 경우 다중 정의와 함께
* 가변인수는 호출될 때 **인수의 갯수만큼의 사이즈를 가진 배열을 만들기 때문에** 성능에 민감한 제품의 경우 사용하기 조심스러움
  * 이는 다중 정의를 통해 우회 가능
  * 메서드 호출의 95%가 3개 이하의 인자를 가진 메서드 호출에 집중된다면, 1~3개의 인수를 받는 메서드를 각각 정의하고, 4개 이상의 인수를 받는 메서드를 가변인수 메서드로 정의
  ```java
  public void foo() {}
  public void foo(int a1) {}
  public void foo(int a1, int a2) {}
  public void foo(int a1, int a2, int a3) {}
  public void foo(int a1, int... args) {}
  ```
### 결론
* 인수의 개수가 유동적일 경우 가변인수를 사용한다.
* 필수 인수가 있을 경우 가변인수와 함께 선언한다.
* 성능에 민감할 경우 다중 정의와 함께 사용한다.
---
[[Prev act >>]](../act4/README.md)  
[[Next act >>]](../act6/README.md)
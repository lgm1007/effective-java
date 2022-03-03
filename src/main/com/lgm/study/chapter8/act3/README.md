# 메서드 시그니처를 신중히 설계하라
### 메서드 설계 요령
1. 메서드 이름을 신중히 짓자
    * 항상 표준 명명 규칙을 따름
    * 이해할 수 있고 같은 패키지에 속한 다른 이름들과 일관되게 지음
    * 긴 이름은 피하되, 애매하면 자바 라이브러리의 API 가이드를 참조함
2. 편의 메서드를 너무 많이 만들지 말자
    * 메서드가 너무 많은 클래스(인터페이스)는 익히고, 사용하고, 문서화하고, 테스트 및 유지보수하기 어려움
    * 클래스나 인터페이스는 자신의 각 기능을 완벽히 수행하는 메서드로 제공해야 함
    * 자주 쓰일 경우에만 별도의 약칭 메서드를 둠 
        * 확신이 서지 않으면 만들지 않음
3. 매개변수 목록은 짧게 유지하자
    * 4개 이하가 무난
    * 같은 타입의 매개변수 여러 개가 연달아 나오는 경우 특히 해로움
        * 사용자가 실수로 매개변수의 순서를 바꿔 수행해도 컴파일되며, 의도와 다르게 수행됨
    * **과하게 긴 매개변수 목록을 짧게 줄이는 방법**
        1. 여러 메서드로 쪼갬
            * 쪼개진 메서드 각각은 원래 매개변수 목록의 부분집합을 받음
        2. 매개변수 여러 개를 묶어주는 도우미 클래스를 만듬
       ```java
        public static void doSomething(int code, String firstName, String lastName, String group, String etc) {
            // ...
        }
       ```
       ```java
       class Helper {
           int code = 1234;
           String firstName = "Deny";
           String lastName = "John";
           String group = "A_Factory";
           String etc = "none";
       }
      
       public static void doSomething(Helper helper) {
            // ...
       }
       ```
        3. 객체 생성에 사용한 빌더 패턴을 메서드 호출에 응용
### 매개변수의 타입으로는 클래스보단 인터페이스가 낫다
* 매개변수로 적합한 인터페이스가 있다면 (이를 구현한 클래스가 아닌) 그 인터페이스를 직접 사용하도록 함
    * 메서드에 HashMap을 넘길 일은 없음. 대신 Map을 사용함 = 다른 Map 구현체도 인수로 건넬 수 있음
* 인터페이스 대신 클래스를 사용하면 클라이언트에게 특정 구현체만 사용하도록 제한하는 것
    * 입력 데이터가 다른 형태로 존재한다면 명시한 특정 구현체의 객체로 옮겨 담느라 복사 비용을 치러야 함
### boolean 보다는 원소 2개짜리 열거 타입이 낫다
* 메서드 이름상 boolean을 받아야 의미가 더 명확해질 경우는 예외
* 열거 타입을 사용하면 코드를 읽고 쓰기가 더 쉬워지며, 나중에 선택지(원소)를 추가하기도 쉬움
* 열거 타입을 사용하면 개별 열거 타입 상수 각각에 특정 동작을 수행하는 메서드를 정의할 수도 있음
```java
public void setConfig(boolean isSet) {
    if (isSet) {
        // ...
    } else { 
        // ... 
    }
}
```
```java
public enum Config {PAUSE, SUCCESS, FAIL}
    // ...
public void setConfig(Config config) {
    switch (config) {
        case PAUSE:
            // ...
        case SUCCESS:
            // ...
        case FAIL:
            // ...
    }
}
```
---
[[Prev act >>]](../act2/README.md)  
[[Next act >>]](../act4/README.md)
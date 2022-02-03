# 비트 필드 대신 EnumSet을 사용하라
### 비트 필드 열거 상수 - 옛날 기법
```java
public class Text {
    public static final int STYLE_BOLD = 1 << 0;    // 1
    public static final int STYLE_ITALIC = 1 << 1;  // 2
    public static final int STYLE_UNDERLINE = 1 << 2;   // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3;   // 8
    
    // 매개변수 styles는 0개 이상의 STYLE_ 상수를 비트별 OR한 값
    public void applyStyles(int style) {
        // ...
    }
}
```
* 열거한 값들이 단독이 아닌 집합으로 사용될 경우, 예전에는 각 상수에 서로 다른 2의 거듭제곱값을 할당한 정수 열거 패턴을 사용함
* 위 코드처럼 비트별 OR을 사용해 여러 상수를 하나의 집합으로 모음
* 이렇게 만들어진 집합을 비트 필드(bit field)라고 함
* 비트 필드를 사용하면 비트별 연산을 사용해 합집합과 교집합같은 집합 연산을 효율적으로 할 수 있었음
* 비트 필드는 정수 열거 상수의 단점을 그대로 지님
  * 비트 필드 값이 그대로 출력되면 단순한 정수 열거 상수를 출력할 때보다 해석하기 훨씬 어려움
  * 비트 필드 하나에 들어있는 모든 원소를 순회하기 어려움
  * 최대 몇 비트가 필요한지를 API 작성 시 미리 예측하여 적절한 타입을 선택해야 함, API를 수정하지 않고는 비트 수를 더 늘릴 수가 없음
### EnumSet - 비트 필드를 대체할 현대적 기법
```java
public class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}
    
    // 어떤 Set을 넘겨도 되나, EnumSet이 가장 좋음
    public void applyStyle(Set<Style> styles) {
        // ...
    }
}
```
* `java.util` 패키지의 EnumSet 클래스는 열거 타입 상수의 값으로 구성된 집합을 효과적으로 표현해 줌
* Set 인터페이스를 완벽히 구현하며, 타입 안전하고, 다른 어떤 Set 구현체와도 함께 사용 가능함
* 난해한 작업들은 EnumSet이 다 처리해주기 때문에, 비트를 직접 다룰 때 겪는 흔한 오류들로부터 해방됨
```java
public class EnumSetTest {
    public static void main(String[] args) {
        text.applyStyle(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
```
* `applyStyle(Set<Style> styles)` 메서드가 EnumSet 이 아닌 `Set`을 받는 것은 다형성 때문
  * 모든 클라이언트가 EnumSet을 건넬 것이라는 짐작이 되는 상황이라도 이왕이면 인터페이스로 받는 게 일반적으로 좋음
  * 특이한 클라이언트가 다른 Set 구현체를 건네도 처리할 수 있음
### 결론
* 열거할 수 있는 타입을 모아 집합 형태로 사용한다고 해도 **비트 필드를 사용할 이유는 없다.**
* EnumSet 클래스가 비트 필드 수준의 명료함과 성능, 열거 타입의 장점까지 제공해 준다.
* EnumSet의 유일한 단점 : 아직 불변 EnumSet을 만들 수 없다. (자바 11까지도 수정되지 않음)
  * 명확성과 성능이 떨어지지만, EnumSet 대신 `Collections.unmodifiableSet`으로 EnumSet을 감싸 사용 가능하다.

---
[[Prev act >>]](../act2/README.md)  
[[Next act >>]](../act4/README.md)
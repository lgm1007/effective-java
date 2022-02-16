# 표준 함수형 인터페이스를 사용하라 
### 표준 함수형 인터페이스
* 람다를 지원하면서 API를 작성하는 모범 사례가 크게 바뀜
  * 상위 클래스의 기본 메서드를 재정의해 원하는 동작을 구현하는 템플릿 메서드의 매력이 크게 줄어듦
  * 현대적인 해법: 같은 효과의 함수 객체를 매개변수로 받는 생성자와 메서드를 더 많이 만들어야 함
  * 함수형 매개변수 타입을 올바르게 선택해야 함
```java
@FunctionalInterface
public interface EldestEntryRemovalFunction<K, V> {
    boolean remove(Map<K, V> map, Map.Entry<K, V> eldest);
}
```
* 위 인터페이스는 잘 동작하지만 굳이 사용할 이유는 없음
  * 자바 표준 라이브러리에 이미 같은 모양의 인터페이스 존재
  * java.util.function 패키지에 다양한 용도의 표준 함수형 인터페이스가 담겨 있음
* 직접 구현하지 말고 표준 함수형 인터페이스를 활용할 것
  * 유용한 디폴트 메서드를 많이 제공하므로 다른 코드와의 상호운용성도 좋아짐
### java.util.function
* 기본 함수형 인터페이스
  * 각각의 기본 인터페이스들은 기본 타입인 int, long, double 타입에 맞게 변형된 형태가 존재함  

|인터페이스|함수 시그니처|예|
|---|---|---|
|`UnaryOperator<T>`|`T apply(T t)`|String::toLowerCase|
|`BinaryOperator<T>`|`T apply(T t1, T t2)`|BigInteger::add|
|`Predicate<T>`|`boolean test(T t)`|Collection::isEmpty|
|`Function<T>`|`R apply(T t)`|Arrays::asList|
|`Supplier<T>`|`T get()`|Instant::now|
|`Consumer<T>`|`void accept(T t)`|System.out::println|

* `Operator` 인터페이스는 인스가 1개인 `UnaryOperator`와 2개인 `BinaryOperator`로 나뉨
  * 반환값과 인수의 타입이 같은 함수를 뜻함
* `Predicate` 인터페이스는 인수 하나를 받아 boolean을 반환하는 함수룰 뚯함
* `Function` 인터페이스는 인수와 반환 타입이 다른 함수를 뜻함
* `Supplier` 인터페이스는 인수를 받지 않고 값을 반환(혹은 제공)하는 함수를 뜻함
* `Consumer` 인터페이스는 인수를 하나 받고 반환값은 없는, 인수를 소비하는 함수를 뜻함
### 표준 함수형 인터페이스 사용
* `Comparator<T>` 인터페이스의 경우 구조적으로 `ToIntBiFunction<T, U>`와 동일하지만 독자적인 인터페이스로 존재해야 하는 이유
    1. API에서 굉장히 자주 사용되는데, 이름이 그 용도를 잘 설명해줌
    2. 구현하는 쪽에서 반드시 지켜야 할 규약을 담고 있음
    3. 비교자들을 변환하고 조합해주는 유용한 디폴트 메서드들을 많이 담고 있음
* Comparator의 특성, 위 특성 중 하나라도 만족한다면 전용 함수형 인터페이스를 구현해야 하는 건 아닌지 고민해보도록 함
* 전용 함수형 인터페이스를 작성하기로 했다면 '인터페이스'임을 명심하여 아주 주의해서 설계할 것
### @FunctionalInterface 어노테이션
* `@Override`를 사용하는 이유와 비슷한 이유로 사용됨
* 프로그래머의 의도를 명시하는 의미로 크게 3가지 목적이 있음
  1. 해당 클래스의 코드나 설명 문서를 읽을 이에게 그 인터페이스가 람다용으로 설계된 것임을 알려줌
  2. 해당 인터페이스가 추상 메서드를 오직 하나만 가지고 있어야 컴파일되게 해줌
  3. 유지보수 과정에서 누군가 실수로 메서드를 추가하지 못하게 막아줌
* **직접 만든 함수형 인터페이스에는 항상 @FunctionalInterface를 달아줌**
### 함수형 인터페이스 사용 시 주의점
* 서로 다른 함수형 인터페이스를 같은 위치의 인수로 받는 메서드들을 다중 정의해서는 안 됨
  * 클라이언트에게 불필요한 모호함만 줄 뿐
### 결론
* 자바 8부터 람다를 지원하기 때문에, 입력값과 반환값에 함수형 인터페이스 타입을 활용하도록 한다.
* 보통 java.util.function 패키지의 표준 함수형 인터페이스를 사용하는 것이 가장 좋은 선택이다.
* 흔치 않지만, 가끔 새로운 함수형 인터페이스를 만들어 사용하는 게 나을 수도 있다.
  * 직접 만든 함수형 인터페이스의 경우 항상 @FunctionalInterface 어노테이션을 달아준다.
---
[[Prev act >>]](../act2/README.md)  
[[Next act >>]](../act4/README.md)
# 로 타입은 사용하지 말라
### 제네릭 클래스, 제네릭 인터페이스
* 클래스와 인터페이스 선언에 타입 매개변수(type parameter)가 쓰이는 경우
    * 예) List 인터페이스는 원소의 타입을 나타내어 완전한 이름은 `List<E>` 이지만 짧게 `List`로 사용함
* 제네릭 클래스와 제네릭 인터페이스를 통틀어 제네릭 타입(Generic type)
* 로 타입(raw type)은 제네릭 타입에서 타입 매개변수를 전혀 사용하지 않을 경우를 의미
    * 예) `List<E>`의 로 타입 : `List`
### 컬렉션의 로 타입 = 따라하지 말 것!
```java
private final Collection stamps = ...;

// Stamp 대신 Coin을 넣는 실수가 있을 수 있다
stamps.add(new Coin(...));  // "unchecked call" 경고를 뱉음
```
* 컬렉션에서 값을 꺼내기 전까지는 문제를 알아채기 어려움
* 로 타입 사용하면 제네릭이 주는 안전성과 표현력 모두 잃게됨
```java
public static void main(String[] args) {
    List<String> strings = new ArrayList<>();
    
    unsafeAdd(strings, Integer.valueOf(42));
    String s = strings.get(0);  // 컴파일러가 자동으로 형변환 코드를 넣어줌
}

// List 로 타입 사용
private static void unsafeAdd(List list, Object o) {
    list.add(o);
}
```
* 위 코드를 실행할 경우, strings.get(0) 결과값을 형변환하려 할 때 `ClassCastException`을 던짐
### 비한정적 와일드카드 사용
##### `Set<?>`와 로 타입인 `Set`의 차이
* <?>의 경우에는 타입 안전하고 로 타입의 경우에는 타입 안전하지 않음
### 로 타입을 사용해도 좋은 예 - instanceof 연산자
* 로 타입이든 비한정적 와일드카드이든 instanceof는 완전히 똑같이 동작
* 이 경우 와일드카드의 물음표가 코드를 지저분하게 만드므로 로 타입을 사용하는 편이 나음
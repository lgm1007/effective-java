# 제네릭과 가변인수를 함께 쓸 때는 신중해라
### 가변인수
* 인수의 개수와 타입이 미리 정해져 있지 않은 인수
* 메서드에 넘기는 인수의 개수를 클라이언트가 조절할 수 있도록 함
    * 하지만, 구현 방식에 허점이 있음
        * 가변인수 메서드 호출 시 가변인수를 담기 위한 배열이 자동으로 생성됨
        * 내부로 감춰야 했을 이 배열을 클라이언트에 노출하는 문제가 있었음
    * 가변인수 메서드 매개변수에 제네릭이나 매개변수화 타입이 포함되면 컴파일 경고 발생
### 실체화 불가 타입
* **act3**에서 실체화 불가 타입은 런타임에서 컴파일 타임에서보다 타입 정보를 적게 담고 있음을 언급
* 또한 거의 모든 제네릭과 매개변수화 타입은 실체화되지 않음
* 이로 인해 메서드를 선언할 때 실체화 불가 타입으로 가변인수 매개변수를 선언하면 컴파일러가 경고를 보냄
```java
class VarargsTest {
    // 가변인수는 내부적으로 배열
    static void dangerous(List<String>... stringLists) {
        List<Integer> intList = List.of(42);
        // List<String>타입은 Object의 하위 클래스로 인식되어 Object[]에 참조될 수 있음
        Object[] objects = stringLists;
        // 힙 오염(heap pollution) : 매개변수화 타입의 변수가 타입이 다른 객체를 참조하면 힙 오염이 발생
        Object[0] = intList;
        // ClassCastException 발생
        String s = stringLists[0].get(0);
    }
}
```
* 위와 같이 타입안정성이 깨지기 때문에 제네릭 가변인수 배열 매개변수에 값을 저장하는 건 안전하지 않음
    * 보통 실무에서는 가변인수 형태를 그대로 받아와서 사용하긴 함
### @SafeVarargs
* 자바 7에서 추가된 어노테이션, 제네릭 가변인수 메서드 작성자가 클라이언트 측에서 발생하는 경고를 숨길 수 있게 함
    * 메서드 작성자가 해당 메서드의 타입 안전함을 보장하는 장치
* 메서드가 가변인수 매개변수를 담는 제네릭 배열에 아무것도 저장하지 않고 그 배열의 참조가 밖으로 노출되지 않는다면 타입 안전함
### 제네릭 매개변수 배열의 참조를 노출하는 건 위험함
```java
static <T> T[] toArray(T... args) {
    return args;
}
```
```java
static <T> T[] pickTwo(T a, T b, T c) {
    switch(ThreadLocalRandom.current().nextInt(3)) {
        case 0: return toArray(a, b);
        case 1: return toArray(b, c);
        case 2: return toArray(c, a);
    }
    throw new AssertionError();
}
```
* 제네릭 가변인수를 받는 `toArray()` 메서드를 호출하는 함수
    * 컴파일러는 `toArray()`에 넘길 T 인스턴스 2개를 담은 varargs 매개변수 배열을 만드는 코드 생성
    * `pickTwo()` 함수에 어떤 타입의 객체를 넘기더라도 담을 수 있는 타입인 Object[] 타입으로 매개변수 배열을 생성할 것
    * 이후 `toArray()` 메서드가 돌려준 이 배열이 그대로 `pickTwo()` 를 호출한 클라이언트까지 전달됨
    * `pickTwo()`는 항상 Object[] 타입을 반환
```java
public static void main(String[] args) {
    String[] attributes = pickTwo("좋은", "빠른", "저렴한");  // ClassCastException
}
```
* 위 코드 실행 시 `pickTwo`의 반환값을 String[] 타입으로 형변환하는 코드를 컴파일러가 자동 실행함
* Object[]는 String[]의 하위 타입이 아니므로 형변환 실패
* **제네릭 가변인수 매개변수 배열에 다른 메서드가 접근하도록 허용하면 안전하지 않다는 점**
* 단 2가지 예외가 존재
    1. @SafeVarargs로 어노테이션된 또다른 가변인수 메서드에 넘기는 것은 가능
    2. 해당 배열 내용의 일부 함수를 호출만 하는 일반 메서드에 넘기는 것도 안전
### 결론
* 가변인수와 제네릭은 궁합이 좋지 않다.
    * 가변인수 기능은 배열을 노출하여 추상화가 완벽하지 못하고, 배열과 제네릭의 타입 규칙이 서로 다르기 때문이다.
* 제네릭 가변인수 매개변수는 타입 안전하지 않지만, 허용된다.
* 메서드에 제네릭 또는 매개변수화된 가변인수 매개변수를 사용하고자 한다면, 우선 그 메서드가 타입 안전한지 확인 후 @SafeVarargs 어노테이션을 달아 사용하는데 불편함이 없게끔 해야 함
---
[[Prev act >>]](../act6/README.md)  
[[Next act >>]](../act8/README.md)
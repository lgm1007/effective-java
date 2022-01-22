# 이왕이면 제네릭 메서드로 만들어라
### 문제가 있는 제네릭 메서드 작성
* 로 타입 사용
```java
class Test {
    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }
}
```
* 컴파일은 되지만 경고 발생
  * 경고를 없애기 위해 해당 메서드를 타입 안전하게 만들어야 함
  * 제네릭 메서드로 만듦
### 제네릭 메서드 작성
```java
public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
    Set<E> result = new HashSet(s1);
    result.addAll(s2);
    return result;
}
```
* 단순한 제네릭 메서드. 이 정도면 충분히 타입 안전함
### 제네릭 싱글턴 팩터리 패턴
```java
private static UnarrayOperator<Object> IDENTITY_FN = (t) -> t;

@SuppressWarnings("unchecked");
public static <T> UnarrayOperator<T> identityFunction() {
    return (UnarrayOperator<T>) IDENTITY_FN;
}
```
* `IDENTITY_FN`을 `UnarrayOperator<T>`로 형변환하면 비검사 형변환 경고가 발생
* 항등 함수(identity function)는 입력값을 그대로 반환하는 함수이므로, `T`가 어떤 타입이든 안전
  * `@SuppressWarning`으로 경고를 숨겨도 됨
### 재귀적 타입 한정
* 자기 자신이 들어간 표현식을 사용하여 타입 매개변수의 허용 범위를 한정할 수 있음
* 주로 Comparable 인터페이스와 사용함
```java
public interface Comparable<T> {
    int compareTo(T o);
}
```
* 타입 매개변수 T는 `Comparable<T>`를 구현한 타입이 비교할 수 있는 원소의 타입을 정의
* String은 `Comparable<String>`, Integer는 `Comparable<Integer>`를 구현하는 방식
### 재귀적 타입 한정을 통해 상호 비교할 수 있음을 표현
```java
class Test {
    public static <E extends Comparable<E>> E max(Collection<E> c);
}
```
* 타입 한정인 `<E extends Comparable<E>>`는 '모든 타입 E는 자신과 비교할 수 있다'라는 의미
  * 상호 비교가 가능하다는 뜻
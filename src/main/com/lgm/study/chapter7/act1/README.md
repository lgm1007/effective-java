# 익명 클래스보다는 람다를 사용하라
* 이전에는 자바에서 함수 타입을 표현할 때 추상 메서드를 하나만 담은 인터페이스를 사용함
* 이런 인터페이스를 함수 객체라고 하여, 특정 함수나 동작을 나타내는 데 사용
### 익명 클래스의 인스턴스를 함수 객체로 사용 - 낡은 기법
```java
public class Test {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
    }
}
```
* 익명 클래스 방식은 코드가 너무 길어짐
    * 함수형 프로그래밍에서 적합하지 않음
* 추상 메서드 하나만 존재하는 인터페이스는 특별한 의미를 인정받았고, 현재 함수형 인터페이스라고 불리는 인터페이스의 인스턴스를 **람다식을 사용해** 만들 수 있게 됨
    * 람다 : 함수나 익명 클래스와 비슷한 개념이나 코드는 훨씬 간결함
### 람다식을 함수 객체로 사용 - 익명 클래스 대체
```java
public class Test {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
    }
}
```
* 람다 매개변수`(s1, s2)`, 반환값의 타입은 각각 `(Comparator<String>)`, `String`, `int`
    * 코드에서는 언급하지 않았는데, 이는 컴파일러가 문맥을 살펴 타입을 추론하기 때문
    * 상황에 따라 타입을 결정하지 못하면 프로그래머가 직접 타입 명시
    * 타입을 명시해야 코드가 명확해지는 경우를 제외하고 **람다의 모든 매개변수 타입은 생략함**
### 람다 사용 시 고려할 점
* 람다는 이름이 없고 문서화도 할 수 없음
    * 코드 자체로 동작이 명확히 설명되지 않거나 코드가 길어지면 람다를 사용하지 말아야 함
        * 람다는 한 줄일 때 가장 좋고 길어야 세 줄안에 끝내는 게 좋음
* 열거 타입 생성자에 넘겨지는 인수들의 타입도 컴파일타임에 추론됨
* 람다는 함수형 인터페이스에서만 쓰임
* 람다는 자신을 참조할 수 없음
* 람다도 익명 클래스처럼 직렬화 형태가 구현별로(가끔 가상머신별로) 다를 수 있음
    * 따라서 람다를 직렬화하는 일은 극히 삼가야 함
### 결론
* 자바 8에서 함수 객체를 구현하는 데 적합한 람다가 도입되었다.
* 람다는 작은 함수 객체를 쉽게 표현할 수 있다.
* 익명 클래스는 함수형 인터페이스가 아닌 타입의 인스턴스를 만들 때만 사용한다.
---
[[Prev act >>]](../../chapter6/act8/README.md)  
[[Next act >>]](../act2/README.md)
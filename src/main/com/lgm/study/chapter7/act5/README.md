# 스트림에서는 부작용 없는 함수를 사용하라
* 스트림은 함수형 프로그래밍에 기초한 패러다임
### 스트림 패러다임
* 계산을 일련의 변환(transformation)으로 재구성하는 부분
* 각 변환 단계는 가능한 이전 단계의 결과를 받아 처리하는 순수 함수여야 함
  * 순수 함수란 오직 입력만이 결과에 영향을 주는 함수
    * 다른 가변 상태를 참조하지 않고, 함수 스스로도 다른 상태를 변경하지 않음
  * 이를 위해 스트림 연산에 건네는 함수 객체는 모든 부작용이 없어야 함
### 스트림 패러다임을 이해하지 못한 채 API만 사용 - 따라하지 말 것
```java
public class Test {
    public static void main(String[] args) {
        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new Scanner("file").tokens()) {
            words.forEach(word -> freq.merge(word.toLowerCase(), 1L, Long::sum));
        }
    }
}
```
* 스트림 코드를 가장한 반복적 코드
* 위 코드의 모든 작업은 종단 연산인 forEach에서 일어남
  * 외부 상태(빈도표)를 수정하는 람다를 실행하면서 문제가 생김
### 스트림 패러다임을 이해해 빈도표를 초기화하는 코드
```java
public class Test {
    public static void main(String[] args) {
        Map<String, Long> freq = new HashMap<>();
        try(Stream<String> words = new Scanner("file").tokens()) {
            words.collect(groupingBy(String::toLowerCase, counting()));
        }
    }
}
```
* forEach문은 종단 연산 중 기능이 가장 적고 반복적이라서 병렬화할 수 없음
* **forEach 연산은 스트림 계산 결과를 보고할 때만 사용할 것**, 계산할 때는 쓰지 말도록 함
### Collectors, 수집기
* java.util.stream.Collectors 클래스는 스트림의 원소들을 객체 하나에 취합하는 여러 메서드를 제공해줌
  * `toList()`, `toSet()`, `toCollection(collectionFactory)`
* 스트림의 원소들을 손쉽게 컬렉션으로 모을 수 있음
* Collectors의 멤버를 정적 import하여 쓰면 스트림 파이프라인의 가독성을 향상시킬 수 있음
### 결론
* 스트림 파이프라인 프로그래밍의 핵심은 부작용 없는 함수 객체에 있다.
  * 스트림뿐 아니라 스트림 관련 객체에 건네지는 모든 함수 객체가 부작용이 없어야 한다.
* 종단 연산 중 forEach는 스트림이 수행한 결과를 보고할 때만 사용해야 한다. 계산 중에 사용하지 않도록 한다.
* 스트림을 올바로 사용하려면 수집기를 잘 알아야 한다.
  * 가장 중요한 수집기 팩토리는 `toList`, `toSet`, `toMap`, `groupingBy`, `joining` 이다.
---
[[Prev act >>]](../act4/README.md)  
[[Next act >>]](../act6/README.md)

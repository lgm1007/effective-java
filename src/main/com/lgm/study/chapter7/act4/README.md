# 스트림은 주의해서 사용하라
* 스트림 API가 제공하는 추상 개념 중 핵심
    1. 스트림(Stream)은 데이터 원소의 유한 또는 무한 시퀀스를 의미
    2. 스트림 파이프라인은 해당 원소들로 수행하는 연산단계를 표현하는 개념
* 스트림의 원소들은 어디서든 올 수 있음
    * 컬렉션 (Collection)
    * 배열 (Array)
    * 파일 (File)
    * 정규표현식 (Regex Pattern Matcher)
    * 난수 생성기 (Random Generator)
    * 다른 스트림 (Other Stream)
* 스트림 안의 데이터 원소들은 객체 참조나 기본 타입(int, long, double)을 지원함
    * Stream : 객체 참조에 대한 스트림
    * IntStream : int 타입에 대한 스트림
    * LongStream : long 타입에 대한 스트림
    * DoubleStream : double 타입에 대한 스트림
### 스트림 파이프라인
* 스트림 파이프라인은 소스 스트림에서 시작해 종단 연산(terminal operation)으로 끝나며, 그 사이에 하나 이상의 중간 연산(intermediate operation)이 있을 수 있음
* 종단 연산

|연산 명|설명|
|:---:|:---:|
|forEach(Consumer<? super T> consumer)|스트림의 요소를 순회|
|count()|스트림 내의 요소 수 반환|
|max(Comparator<? super T> comparator)|스트림 내의 최대 값 반환|
|min(Comparator<? super T> comparator)|스트림 내의 최소 값 반환|
|allMatch(Predicate<? super T> predicate)|스트림 내에 모든 요소가 predicate 함수에 만족할 경우 true|
|anyMatch(Predicate<? super T> predicate)|스트림 내에 하나의 요소라도 predicate 함수에 만족할 경우 true|
|noneMatch(Predicate<? super T> predicate)|스트림 내에 모든 요소가 predicate 함수에 만족하지 않는 경우 true|
|sum()|스트림 내의 요소의 합 (IntStream, LongStream, DoubleStream)|
|average()|스트림 내의 요소의 평균 (IntStream, LongStream, DoubleStream)|
* 중간 연산

|연산 명|설명|
|:---:|:---:|
|filter(Predicate<? super T> predicate)|predicate 함수에 맞는 요소만 사용하도록 필터|
|map(Function<? super T, ? extends R> function)|요소 각각의 function 적용|
|flatMap(Function<? super T, ? extends R> function)|스트림의 스트림을 하나의 스트림으로 변환|
|distinct()|중복 요소 제거|
|sort()|기본 정렬|
|sort(Comparator<? super T> comparator|comparator 함수를 이용하여 정렬|
|skip(long n)|n개 만큼의 스트림 요소를 건너뜀|
|limit(long maxSize)|maxSize 갯수만큼만 출력|

### filter, map, flatMap
#### filter
* 특정 조건으로 컨텐츠를 필터링하는 기능
* `filter(조건)`으로 사용, 이 조건에 해당하는 item만 리턴
* 예)
```java
List<String> list = Arrays.asList("a1", "a2", "b1", "b2", "c1", "c2", "c3");
Stream<String> filtered = list.stream()
        .filter(s -> s.startsWith("c"));    // c로 시작하는 아이템만 리턴
filtered.forEach(System.out::println);      // c1, c2, c3
```
#### map
* 각각의 item을 변경하여 새로운 컨텐츠를 생성하는 기능
* `map(함수)`로 어떻게 아이템을 변경할지 함수로 정의
```java
List<String> list = Arrays.asList("a1", "a2", "b1", "b2", "c1", "c2", "c3");
list.stream().map(s -> s.toUpperCase())     // 알파벳 대문자로 변환
        .forEach(System.out::println);      // A1, A2, B1, B2, C1, C2, C3
```
#### flatMap
* 여러 개의 스트림을 하나로 합쳐줌
  * 복잡한 스트림을 간단한 스트림으로 만들어줄 때 사용
```java
String[][] arrays = new String[][]{ {"a1", "a2"}, {"b1", "b2"}, {"c1", "c2", "c3"} };
Stream<String> stream = Arrays.stream(arrays)   // Stream<String[]> 타입의 스트림 반환
        .flatMap(s -> Arrays.stream(s));       // Stream<String> 타입의 스트림으로 변환
stream.forEach(System.out::println);          // a1, a2, b1, b2, c1, c2, c3
```
### 스트림의 지연 평가 (Lazy evaluation)
* 스트림에 대한 평가는 종단 연산이 호출될 때 이뤄짐
* 종단 연산에 쓰이지 않는 데이터 원소는 계산에 쓰이지 않음
* 즉, **종단 연산이 없는 스트림 파이프라인은 아무런 일도 일어나지 않음**
### 스트림을 과도하게 사용한 예 - 따라하지 말 것
```java
class OverUseStream {
    public static void main(String[] args) throws IOException {
        File dictionary = new File(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        
        try(Stream<String> words = Files.lines(dictionary.toPath())) {
            words.collect(
                    groupingBy(word -> word.chars().sorted()
                    .collect(StringBuilder::new,
                            (sb, c) -> sb.append((char)c),
                            StringBuilder::append).toString()))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .map(group -> group.size() + ": " + group)
                    .forEach(System.out::println);
        }
    }
}
```
* 스트림을 과도하게 사용하면 가독성이 떨어지며, 유지보수가 어려워짐
### 스트림을 적절하게 사용한 경우
```java
class ProperUseStream {
    public static void main(String[] args) throws IOException {
        File dictionary = new File(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        
        try(Stream<String> words = Files.lines(dictionary.toPath())) {
            words.collect(groupingBy(word -> alphabetize(word)))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(group -> System.out.println(group.size() + ": " + group));
        }
    }
}
```
* 스트림을 적절하게 사용하면 코드가 간단명료해짐
* 람다에서는 타입 추론 기능을 사용하기 때문에 타입명을 생략함
    * 따라서 매개변수 이름을 잘 지어줘야 스트림 파이프라인에서의 가독성이 보장됨
### 기존 코드는 필요한 경우에만 스트림으로 리팩토링할 것
* 스트림으로 바꾸는 게 가능할지라도, 가독성과 유지보수 측면에서 손해를 볼 경우가 있을 수 있음
* 무작정 스트림으로 바꾸기보다는 적절히 사용할 것
### 코드블록 VS 스트림
* 코드블록
    * 범위(scope) 내 지역변수를 읽고 수정할 수 있음
    * 코드블록에서는 return으로 메서드를 빠져나올 수 있음
    * 코드블록에서는 break, continue 문을 통해 블록 바깥으로 나갈 수 있음
    * 메서드 선언에 명시된 예외를 던질 수 있음
* 스트림
    * 원소들의 시퀀스를 일관되게 변환 가능
    * 원소들의 시퀀스 필터링 가능
    * 원소들의 시퀀스를 하나의 연산을 사용해 결합함 (sum, min ...)
    * 원소들의 시퀀스를 컬렉션에 모을 수 있음
    * 원소들의 시퀀스에서 특정 조건을 만족하는 원소를 찾을 수 있음
### 결론
* 스트림과 함수형 프로그래밍에 익숙한 프로그래머는 스트림 방식이 좀 더 명확하다.
* 스트림과 일반 반복문을 적절하게 사용해야 한다.
---
[[Prev act >>]](../act3/README.md)  
[[Next act >>]](../act5/README.md)
# 반환 타입으로는 스트림보다는 컬렉션이 낫다
### 반복
* 스트림은 반복(iteration)을 지원하지 않음
  * 스트림과 반복을 알맞게 조합해야 좋은 코드가 나옴
#### `Stream<E>`를 `Iterable<E>`로 중개해주는 어댑터
```java
public static <E> Iterable<E> iterableOf(Stream<E> stream) {
    return stream::iterator;
}
```
```java
for (ProcessHandle ph : iterableOf(ProcessHandle.allProcesses())) {
}
```
* iterableOf 메서드를 통해 명시적으로 Iterable으로 반환할 수 있음
#### API에서 Iterator만 반환하는 경우
* 자바에서 `Iterator -> Stream`을 위한 어댑터는 제공하지 않지만 쉽게 구현 가능함
```java
// Iterable<E>를 Stream<E>로 중개해주는 어댑터
public static<E> Stream<E> streamOf(Iterable<E> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false);
}
```
* for-each를 사용하는 개발자와 Stream을 사용하는 개발자 모두 배려하여 Stream과 Iterable을 동시에 제공할 수 있도록 하는 게 좋음
* 원소 시퀀스를 반환하는 API 반환 타입에는 Collection이나 그 하위타입을 쓰는 것이 일반적
### 컬렉션 내의 시퀀스가 크면 전용 컬렉션을 구현하라
* 반환하는 시퀀스의 크기가 메모리에 올려도 안전할 만큼 작다면 `ArrayList`나 `HashSet`같은 표준 컬렉션 구현체를 반환하는 게 나을 수 있음
* 단지 컬렉션을 반환한다는 이유로 덩치 큰 시퀀스를 메모리에 올려선 안 됨
* 반환할 시퀀스가 크지만 표현을 간결하게 할 수 있다면 전용 컬렉션 구현을 고려, AbstractList를 이용하면 전용 컬렉션을 손쉽게 구현 가능
  * AbstractCollection을 활용해 컬렉션 구현체를 작성할 경우, Iterable용 메서드 외에 contains()와 size()만 구현하면 됨
  * 시퀀스 내용을 확정할 수 없는 등의 이유로 contains()와 size()를 구현할 수 없으면 컬렉션보다는 Stream이나 Iterable을 반환하는 게 더 나음
### 스트림 반환하기
#### 입력 리스트의 모든 부분리스트를 스트림으로 반환하기
```java
public class SubLists {
    public static <E> Stream<List<E>> of(List<E> list) {
        return Stream.concat(Stream.of(Collections.emptyList()), prefixes(list).flatMap(SubLists::suffixes));
    }

    private static <E> Stream<List<E>> prefixes(List<E> list) {
        return IntStream.rangeClosed(1, list.size())
                .mapToObj(end -> list.subList(0, end));
    }

    private static <E> Stream<List<E>> suffixes(List<E> list) {
        return IntStream.range(0, list.size())
                .mapToObj(start -> list.subList(start, list.size()));
    }
}
```
```java
public class SubLists {
    public static <E> Stream<List<E>> of(List<E> list) {
        return IntStream.range(0, list.size())
                .mapToObj(start -> IntStream.rangeClosed(start + 1, list.size())
                .mapToObj(end -> list.subList(start, end)))
                .flatMap(x -> x);
    }
}
```
* flatMap() : .map()은 입력한 원소를 그대로 스트림으로 반환하는 반면, flatMap()은 입력한 원소를 가장 작은 단위의 단일 스트림으로 반환
```java
// flatMap() 동작 간단설명
Stream<String[]> -> flatMap -> Stream<String>
Stream<Set<String>> -> flatMap -> Stream<String>
Stream<List<String>> -> flatMap -> Stream<String>
```
### 결론
* 원소 시퀀스를 반환하는 메서드를 작성할 때는 Stream과 Iterator에서의 사용을 모두 고려해야 한다.
  * 되도록 컬렉션으로 반환하는게 좋다.
* 반환 전부터 이미 원소들을 컬렉션에 담아 관리하고 있거나 컬렉션을 하나 더 만들어도 될 정도로 원소의 개수가 적다면 ArrayList 같은 표준 컬렉션에 담아 반환한다.
  * 그렇지 않으면, 전용 컬렉션을 구현해야 할지 고려한다.
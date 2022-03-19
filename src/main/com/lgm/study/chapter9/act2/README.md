# 전통적인 for문보다는 for-each문을 사용하라
### 전통적인 for
#### 컬렉션 순회하기
* 컬렉션의 경우 반복자를 통해서 순회할 수도 있고, 인덱스 값으로 순회할 수 있음
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> resultNumbers = new ArrayList<>();

// 반복자 순회
for (Iterator<Integer> i = numbers.iterator(); i.hasNext(); ) {
    resultNumbers.add(i.next());
}

// 인덱스 순회
for (int i = 0; i < numbers.size(); i++) {
    numbers.get(i);
}
```
#### 배열 순회
* 배열은 인덱스 값으로 순회
### 반복자를 사용했을 때 주의점
* next() 메서드
  * next 메서드는 반복자의 커서를 움직임과 동시에 element를 가져옴
  * 잘못 사용 예
  ```java
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

    int count = 0;

    for (Iterator<Integer> i = numbers.iterator(); i.hasNext(); ) {
        for (Iterator<Integer> j = numbers.iterator(); j.hasNext(); ) {
            i.next();
            j.next();
            count++;
        }
    }
  ```
  * 36번을 반복할거라 기대하지만 실제로는 6번밖에 반복하지 않음
  * i 반복자의 next 메서드가 2번째 for 문에서 돌고 있기 때문
  * 위 코드 수정
  ```java
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    int count = 0;

    for (Iterator<Integer> i = numbers.iterator(); i.hasNext(); i.next()) {
        for (Iterator<Integer> j = numbers.iterator(); j.hasNext(); j.next()) {
            count++;
        }
    }
  ```
  * 이러한 실수를 방지하기 위해 나온 것이 향상된 for문
### for-each (Enhanced for)
* 반복자나 인덱스를 사용하는 것에 비해 가독성이 훨씬 좋고 순서에 따라 처리 가능
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> resultNumbers = new ArrayList<>();

for (Integer number : numbers) {
    resultNumbers.add(number);
}
```
* 향상된 for 문은 내부적으로 Iterable, Iterator 인테페이스를 사용
  * 향상된 for 문을 사용하기 위해선 Iterable 인터페이스를 구현한 객체이어야 함
* 향상된 for 문은 내부적으로 메서드를 호출하는 비용이 들기 때문에, 전통적인 for 문보다는 느림
* 항상 전체를 순회하기 때문에 중간부터 순회하거나 순회 도중 element를 삭제하는 경우는 불가능
### Iterable 인터페이스의 forEach
* Java 8에서 추가된 default 메서드인 forEach()
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> resultNumbers = new ArrayList<>();

numbers.forEach(resultNumbers::add);
```
* 내부에서 향상된 for 문을 사용함
### 결론
* 가독성과 실수를 예방하는 면에서 전통적인 for문보다는 향상된 for-each문이 좋다.
* 각각의 장단점을 알고 상황에 따라 잘 선택하여 사용해야 한다.
---
[[Prev act >>]](../act1/README.md)  
[[Next act >>]](../act3/README.md)
# 커스텀 직렬화 형태를 고려해보라
### 고민 후 괜찮을 경우에만 기본 직렬화 형태를 사용하라
* 유연성, 성능, 정확성 측면에서 신중히 고민 후 합당하다 판단될 경우에만 사용해야 함
* 직접 설계하더라도 기본 직렬화 형태와 거의 같은 결과가 나올 경우에만 사용해야 함
### 직렬화가 적합한 경우
```java
public class Name implements Serializable {
    
    public Name(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }
    
    /**
     * 성, Not Null
     * @serial 
     */
    private final String lastName;

    /**
     * 이름, Not Null
     * @serial
     */
    private final String firstName;

    /**
     * 중간이름, 중간이름이 없다면 null
     * @serial
     */
    private final String middleName;
}
```
* 위 클래스의 필드들은 위 논리적 구성요소를 정확히 반영함
* 세 필드 모두 `private`이 달려 있음
* 직렬화 시 모두 공개되기 때문에 공개 API에 속함
* `@serial` : 이러한 `private` 필드를 javadoc 에 포함하도록 알려주는 역할
### 직렬화가 적합하지 않은 경우
```java
public class StringList implements Serializable {
    
    private final int size = 0;
    private Entry head = null;
    
    private static class Entry implements Serializable {
        String data;
        Entry next;
        Entry previous;
    }
}
```
* 논리적으로는 일련의 문자열을 표현하는데, 물리적으로는 double linked list로 연결
* 기본 형태 직렬화를 적용한다면, 각 노드의 양방향 연결 정보를 포함해 `Entry`의 모든 정보를 기록할 것
* 결국, 객체의 물리적 표현(= double linked list)와 논리적 표현(문자열 리스트)의 차이가 발생함
* 이런 차이가 발생 시 직렬화 형태를 사용하게 되면 아래 형태의 문제가 발생함
    1. 공개 API가 현재 내부 표현 방식에 영구히 묶임
    2. 너무 많은 공간 차지
    3. 시간이 많이 걸림
    4. stack overflow를 일으킬 수 있음
### HashTable 
* 내부적으로 `Entry`, `EntrySet` 등을 사용하지만 직렬화에 포함되지 않음
* 어떤 `Entry`가 어느 버킷에 담기는지는 hash code에 결정되는데, 그 계산 방식은 구현마다 다름
* `HashTable`이 기본 직렬화를 사용한다면 불변식이 훼손된 객체들이 생길 수 있음
---
[[Prev act >>]](../act2/README.md)  
[[Next act >>]](../act4/README.md)
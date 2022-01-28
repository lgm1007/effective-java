# 배열보다는 리스트를 사용하라
### 공변과 실체화
* 배열은 공변(covariant)임
    * Sub가 Super의 하위 타입이면, 배열 Sub[]는 배열 Super[]의 하위 타입이 됨
    * 공변 : 함께 변한다
* 제네릭은 불공변(invariant)임
    * 서로 다른 타입 Type1과 Type2가 있을 때, `List<Type1>`은 `List<Type2>`의 하위 타입도 아니고 상위 타입도 아님
#### 런타임에 실패한다
```java
class ArrayTest {
    public static void main(String[] args) {
        Object[] objects = new Long[1];
        objects[0] = "String";  // 타입이 달라 문법에 맞지 않음
    }
}
```
```java
class ListTest {
    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<Long>();
        objectList.add("String");   // 타입이 달라 add 불가능
    }
}
```
* 위 두 경우 모두 Long용 저장소에 String을 넣을 수 없음
* 배열에서는 위와 같은 실수를 런타임에서야 알게 되지만, 리스트는 컴파일할 때 바로 알 수 있음
#### 실체화
* 배열은 실체화됨
    * 배열은 런타임에도 자신이 담기로 하는 원소의 타입을 확인함
    * 따라서 Long 배열에 String 원소를 넣으려 하면 예외 발생(ArrayStoreException)
* 제네릭은 타입 정보가 런타임에서 소거됨
    * 원소 타입을 컴파일에만 검사 가능하며 런타임에서는 알 수 없음
* 이러한 차이로 제네릭과 배열은 잘 어우러지지 못함
### 제네릭 배열을 만들지 못하는 이유
* 제네릭 배열을 만들게 되면 타입 안전하지 않음
* 이를 허용하면 컴파일러가 자동 생성한 형변환 코드에서 예외 발생할 수 있음
```java
class GenericArray {
    public static void main(String[] args) {
        List<String>[] stringLists = new List<String>[1];    // 1.
        List<Integer> integerList = List.of(30);    // 2.
        Objecet[] objects = stringLists;             // 3.
        objects[0] = integerList;                   // 4.
        String s = stringLists[0].get(0);               // 5.
    }
}
```
1. 허용된다고 가정
2. List<Integer> 생성
3. 1에서 생성한 `List<String>`배열을 Object 배열에 할당, 배열은 공변이므로 문제가 없음
4. 2에서 생성한 `List<Integer>` 인스턴스를 Object 배열의 첫 번째 원소로 저장
    * 제네릭의 소거 방식으로, 런타임에서 `List<Integer>`의 인스턴스의 타입은 단순히 List가 되고, `List<Integer>[]` 인스턴스의 타입은 List[]가 됨
    * 따라서 문제되지 않고 동작 성공
5. `List<String>` 원소만 담겠다고 선언한 `stringList` 배열에 `List<Integer>` 원소가 저장되어 있음, 그리고 이 배열의 첫 리스트에서 값을 꺼내려 함
    * 컴파일러는 꺼낸 값을 자동으로 String 으로 형변환하는데, 실제 값은 Integer 타입이 들어있으므로 `ClassCastException` 발생
### 결론
* 배열은 공변이고 실체화되는 반면, 제네릭은 불공변이고 타입 정보가 소거됨
* 따라서 배열은 런타임에는 타입 안전하지만 컴파일에는 그렇지 않고, 제네릭은 이와 반대
* 배열과 제네릭을 같이 쓰기에는 쉽지 않음, 만약 둘을 같이 쓰다가 경고 및 오류가 발생하면 가장 먼저 배열을 리스트로 대체하는 방법으로 적용할 것
---
### 개인적 생각
    실제로 코딩할 때 배열보다는 리스트를 자주 사용 
        - 이는 위 내용처럼 공변이나 타입 안전성의 이유에서가 아니다.
        - 단순히 원소를 추가/제거하는데 리스트가 편했기 때문이다.
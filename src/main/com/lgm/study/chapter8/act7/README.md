# 옵셔널 반환은 신중히 하라
### 메서드가 특정 조건에서 값을 반환할 수 없을 때 사용했던 방법
#### 자바 8 이전에 사용했던 방법
1. 예외를 던짐
    * 예외를 던질 경우 문제점
      * 예외는 정말 예외적인 상황에서만 사용해야 함
      * 예외를 생성할 때 스택 추적 전체를 캡처하므로 비용이 비쌈
2. Null을 반환함
    * Null을 반환할 경우 문제점
      * 메서드를 호출한 부분에서 별도의 null 처리 코드를 작성해야 함
      * null 처리를 작성하지 않은 경우 NullPointerException 예외 발생 가능
#### 자바 8 이후 추가된 방법
* 옵셔널(Optional) 반환
```java
public class MemberRepository {

    private static final Map<Long,Member> REPO = new HashMap<>();
    
    public static Optional<Member> optionalFindByKey(Long key) {
        if (REPO.containsKey(key)) {
            return Optional.of(REPO.get(key));
        }
        return Optional.empty();
    }
}
```
* Optional은 null이 아닌 T 타입 참조를 담거나, 아무것도 담지 않을 수 있음
* Optional은 원소를 최대 1개 가질 수 있는 불변 컬렉션
* Optional을 반환하는 메서드는 예외를 던지는 메서드보다 유연하고 사용하기 쉬우며, null을 반환하는 방법보다 오류 가능성이 적음
### 옵셔널을 반환값으로 사용하면 안되는 경우
1. 컬렉션, 스트림 배열, 옵셔널같은 **컨테이너 타입**은 옵셔널로 감싸면 안 됨
```java
//따라하지 말 것
List<String> emptyList = new ArrayList<>();
Optional<List<String>> list = Optional.of(emptyList);
if(list.isPresent()){
    System.out.println("빈 리스트이지만 사용가능 객체로 볼 것 인가??"); 
}else {
    System.out.println("빈 리스트이지만 사용 불가능 객체로 볼 것인가??");
}
```
컨테이너 타입은 그대로 빈 컨테이너 타입으로 반환할 것  
2. **박싱된 기본타입을 담은 옵셔널을 반환하는 경우**는 없어야 함
```java
// 따라하지 말 것
Optional<Integer> integer = Optional.of(10);
Optional<Long> string = Optional.of(10L);
```
박싱된 기본타입을 담는 옵셔널은 기본 타입 자체보다 무거울 수밖에 없음  
int, long, double **전용 옵셔널 클래스**를 사용해야 함  
### 결론
* 옵셔널은 결과가 없을 수 있으며, 클라이언트가 해당 상황을 특별하게 처리해야 한다면 옵셔널을 반환한다.
* 옵셔널을 반환값 이외의 용도로 사용하지 않는다.
* 옵셔널은 한번 감싸는 행위로 성능의 저하가 뒤따른다.
  * 따라서 성능에 민감한 메서드라면 null을 반환하거나 예외를 던지는 편이 나을 수 있다.
---
[[Prev act >>]](../act6/README.md)  
[[Next act >>]](../act8/README.md)
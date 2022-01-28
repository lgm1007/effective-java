# 비검사 경고를 제거하라
* 제네릭을 사용하기 시작하면 수 많은 컴파일러 경고를 보게 됨
### 컴파일러 경고
* 비검사 형변환 경고
* 비검사 메서드 경고
* 비검사 매개변수화 가변인수 타입 경고
* 비검사 변환 경고
* 대부분의 비검사 경고는 컴파일러가 무엇이 잘못되었는지 알려줌
```java
Set<T> exampleSet = new HashSet();
```
위를 컴파일할 경우
```java
warning: [unchecked] unchecked conversion
Set<T> exampleSet = new HashSet();
                    ^
required: Set<T>
found: HashSet
```
다음과 같이 수정하면 경고가 사라짐
```java
Set<T> exampleSet = new HashSet<>();
```
### 할 수 있는 한 모든 비검사 경고를 제거하라
* 비검사 경고를 모두 제거하면 해당 코드는 타입 안전성이 보장됨
* 경고를 제거할 수 없지만 타입 안전하다고 확신하는 경우
  * `@SuppressWarnings("unchecked")` 어노테이션으로 경고를 숨길 수 있음
  * 확실하게 검증하고 사용해야 함
### @SuppressWarnings
* 개별 지역변수 선언부터 클래스 전체까지 어떤 선언에도 달 수 있음
* 가능한 좁은 범위에 적용하도록 해야 함
  * 보통 변수선언, 아주 짧은 메서드 또는 생성자
```java
public <T> T[] toArray(T[] a) {
    if(a.length < size) {
        // 지역변수에 어노테이션을 달아 좁은 범위에 적용
        // 생성한 배열과 매개변수로 받은 배열의 타입이 모두 T[]로 같음
        @SuppressWarnings("unchecked") T[] result = (T[]) Arrays.copyOf(data, size, a.getClass());
        return result;
    }
    
    ...
}
```
* `@SuppressWarnings("unchecked")` 어노테이션을 사용하면 경고를 무시하는 이유를 주석으로 남겨야 함
### 결론
* 비검사 경고는 무시해서는 안 되며, 최대한 제거해야 함
* 경고를 없앨 방법을 찾지 못했다면 그 코드가 안전하다는 것을 증명하고 가능한 범위를 좁혀 `@SuppressWarnings("unchecked")` 어노테이션으로 경고를 숨김
* 경고를 숨길 경우 경고를 숨겨도 안전한 이유를 주석으로 남겨야 함
---
### 개인적 생각
    실제 프로젝트를 진행할 때나 실무에서 이런 비검사 경고를 본 적은 없었다.
        - IDE에서 자동 완성으로 HashSet<>(); 의 형태를 만들어 줬기 때문
    익숙하게 사용했던지라 이런 경고가 있다는 사실을 새롭게 알게 됨
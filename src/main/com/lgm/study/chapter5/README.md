# Chapter 5
## 제네릭
### 제네릭을 지원하기 전에는 컬렉션에서 객체를 꺼낼 때마다 형변환을 해야 했음
### 제네릭을 사용하면 컬렉션이 담을 수 있는 타입을 컴파일러에 알려주게 됨
### 제네릭의 이점을 최대화, 단점을 최소화하는 방법
### 관련 용어 정리
|한글|영문| 예 |
|-----|-----|-----|
|매개변수화 타입|parameterized type|`List<String>`|
|실제 타입 매개변수|actual type parameter|`String`|
|제네릭 타입|generic type|`List<E>`|
|정규 타입 매개변수|formal type parameter|`E`|
|비한정적 와일드카드 타입|unbounded wildcard type|`List<?>`|
|로 타입|raw type|`List`|
|한정적 타입 매개변수|bounded type parameter|`<E extends Number>`|
|재귀 타입 한정|recursive type bound|`<T extends Comparable<T>>`|
|한정적 와일드카드 타입|bounded wildcard type|`List<? extends Number>`|
|제네릭 메서드|generic method|`static <E> List<E> asList(E[] a)`|
|타입 토큰|type token|`String.class`|
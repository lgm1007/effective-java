# ordinal 인덱싱 대신 EnumMap을 사용하라
### ordinal 메서드
* 배열이나 리스트에서 원소를 꺼낼 때 Enum의 ordinal 메서드로 인덱스를 얻는 코드
```java
class Plant {
    enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}
    final String name;
    final LifeCycle lifeCycle;

    public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }
}
```
```java
//비검사 형변환 경고
Set<Plant>[] plantsByLifeCycle =
        (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];

for(int i = 0; i < plantsByLifeCycle.length; i++)
    plantsByLifeCycle[i] = new HashSet<>();

 Plant[] garden = new Plant[6];
 garden[0] = new Plant("가", Plant.LifeCycle.ANNUAL);
 garden[1] = new Plant("나", Plant.LifeCycle.BIENNIAL);
 garden[2] = new Plant("다", Plant.LifeCycle.PERENNIAL);
 garden[3] = new Plant("마", Plant.LifeCycle.ANNUAL);
 garden[4] = new Plant("바", Plant.LifeCycle.BIENNIAL);
 garden[5] = new Plant("사", Plant.LifeCycle.PERENNIAL);

for (Plant p : garden) {
    plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
}

for(int i =0; i < plantsByLifeCycle.length; i++) {
    System.out.printf("%s : %s%n",
            Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
}
```
* 위 코드의 문제점
  1. 배열과 제네릭은 호환되지 않으므로 비검사 형변환 경고
  2. 배열은 각 인덱스의 의미를 모르니 마지막 출력문 코드처럼 출력결과에 직접 레이블을 달아야 함
  3. 코드가 정확한 정수값을 사용한다고 보증해야 함
      * 실수가 생기면 잘못된 동작을 계속 수행하거나, Exception이 던져짐
### 해결 - EnumMap 사용
```java
Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle =
  new EnumMap<>(Plant.LifeCycle.class);

for (Plant.LifeCycle lc : Plant.LifeCycle.values())
  plantsByLifeCycle.put(lc,new HashSet<>());

Plant[] garden = new Plant[6];
garden[0] = new Plant("가", Plant.LifeCycle.ANNUAL);
garden[1] = new Plant("나", Plant.LifeCycle.BIENNIAL);
garden[2] = new Plant("다", Plant.LifeCycle.PERENNIAL);
garden[3] = new Plant("마", Plant.LifeCycle.ANNUAL);
garden[4] = new Plant("바", Plant.LifeCycle.BIENNIAL);
garden[5] = new Plant("사", Plant.LifeCycle.PERENNIAL);

for(Plant p : garden) {
  plantsByLifeCycle.get(p.lifeCycle).add(p);
}

System.out.println(plantsByLifeCycle);
```
* ordinal 메서드를 사용한것과 다른 장점
  * 형변환이 필요없음
  * 맵의 키로 출력용 문자열을 제공하기 때문에, 출력 결과에 직접 레이블을 달지 않아도 됨
  * 배열의 인덱스를 계산하는 코드를 직접 짤 필요 없음
  * EnumMap은 내부적으로 배열을 사용하기 때문에 ordinal 메서드를 쓰는것과 비슷한 성능임
* EnumMap의 생성자가 받는 키 타입의 Class 객체(`Plant.LifeCycle.class`)는 한정적 타입 토큰으로, 런타임 시 제네릭 타입 정보를 제공해 줌
### 코드 최적화 - Stream 사용
```java
Plant[] garden = new Plant[6];
garden[0] = new Plant("가", Plant.LifeCycle.ANNUAL);
garden[1] = new Plant("나", Plant.LifeCycle.BIENNIAL);
garden[2] = new Plant("다", Plant.LifeCycle.PERENNIAL);
garden[3] = new Plant("마", Plant.LifeCycle.ANNUAL);
garden[4] = new Plant("바", Plant.LifeCycle.BIENNIAL);
garden[5] = new Plant("사", Plant.LifeCycle.PERENNIAL);

System.out.println(Arrays.stream(garden)
        .collect(groupingBy(p -> p.lifeCycle)));
```
* Stream 사용 시 코드는 단순해지지만, EnumMap이 아닌 고유한 Map 구현체를 사용하기 때문에 EnumMap의 공간 및 성능 이점이 사라짐
```java
Plant[] garden = new Plant[6];
garden[0] = new Plant("가", Plant.LifeCycle.ANNUAL);
garden[1] = new Plant("나", Plant.LifeCycle.BIENNIAL);
garden[2] = new Plant("다", Plant.LifeCycle.PERENNIAL);
garden[3] = new Plant("마", Plant.LifeCycle.ANNUAL);
garden[4] = new Plant("바", Plant.LifeCycle.BIENNIAL);
garden[5] = new Plant("사", Plant.LifeCycle.PERENNIAL);

System.out.println(Arrays.stream(garden)
                   .collect(groupingBy(p -> p.lifeCycle,
                                       () -> new EnumMap<>(Plant.LifeCycle.class), toSet())));
```
* Map 구현체를 EnumMap으로 사용하기
* `PLANT.ANNUAL`이 하나도 존재하지 않으면 EnumMap 사용 버전에서는 맵을 3개 만들지만, Stream을 사용하면 2개만 만듬
### 결론
* 배열의 인덱스를 얻기 위해 ordinal을 쓰는 건 일반적으로 좋지 않다.
* 대신 EnumMap을 사용하자.
---
[[Prev act >>]](../act3/README.md)  
[[Next act >>]](../act5/README.md)
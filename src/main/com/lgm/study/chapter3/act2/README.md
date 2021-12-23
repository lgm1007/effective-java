# equals를 재정의하려거든 hashCode도 재정의하라
### 위 제목이 곧 hashCode 의 일반 규약
### 세부적인 일반규약
* hashCode 메서드를 반복해서 호출해도 항상 같은 결과를 반환해야 한다.
* `equals(Object)`가 두 객체를 같다고 판단했다면, 두 객체의 hashCode는 똑같은 값을 반환해야 한다. (가장 중요)
* `equals(Object)`가 두 객체를 다르다고 판단했더라도, 두 객체의 hashCode가 서로 다른 값을 반환할 필요는 없다. (단 다른 객체에 대해서 다른 값을 반환해야 hashTable에서의  성능이 좋아진다.)
### hashCode 메서드 재정의 시 핵심필드를 생략하는 실수를 하면 안됨
### AutoValue 사용하기 (자바6부터 지원, 권장)
* AutoValue 라이브러리 사용하면 자동으로 equals, hashCode, 그리고 toString 메서드까지 자동으로 재정의해줌
* 사용법 : 클래스에 `@AutoValue` 어노테이션만 추가해줌
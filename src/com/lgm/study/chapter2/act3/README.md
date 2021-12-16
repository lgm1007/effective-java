# private 생성자나 열거 타입으로 싱글턴임을 보증하라
- 싱글턴객체의 예로는 무상태(stateless) 객체나 설계상 유일해야 하는 시스템 컴포넌트
### private 생성자로 싱글턴 만들기
* 1. public static 멤버가 final 필드인 방식
* 2. 정적 팩토리 메서드를 public static 멤버로 제공
### 열거 타입으로 싱글턴 만들기

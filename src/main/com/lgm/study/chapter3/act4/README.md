# clone 재정의는 주의해서 진행하라
### cloneable 인터페이스
* 상속받은 클래스가 복제해도 되는 클래스임을 명시하는 용도
### Object clone 메서드 일반 규약
* `x.clone() != x` 는 true (원본 객체와 복사 객체는 서로 다름)
* `x.clone().getClass() == x.getClass()` 는 true, 하지만 반드시 만족하지 않음
* `x.clone().equals(x)` 는 true, 하지만 필수는 아님
* `x.clone().getClass() == x.getClass()`, `super.clone()` 을 호출해서 얻은 객체를 clone 메서드가 반환하면, 이 식은 true
  * clone 메서드가 `super.clone()`이 아닌 생성자를 호출해 얻은 인스턴스를 반환하더라도 컴파일 시 문제가 되지 않지만 해당 클래스의 <u>하위 클래스에서 `super.clone()`을 호출</u>한다면 하위 클래스 타입을 반환하지 않고 <u>상위 클래스 타입을 반환</u>하기에 문제가 생길 수 있음
### clone 재정의 시 주의사항
* clone 은 원본 객체에 아무런 영향을 끼치지 않는 동시에 복사된 객체의 불변식을 보장해야 함
* 복제할 수 있는 클래스를 만들기 위해 일부 필드에서 `final` 한정자를 제거해야 할 수 있음
* 재정의될 수 있는 메서드를 호출하지 않아야 함
### 복사 생성자와 복사 팩터리 메서드
* Cloneable 의 clone 메서드 대신 복사 생성자와 복사 팩터리 메서드를 사용
```java
public Yum(Yum yum) {...}
public static Yum newInstance(Yum yum) {...}
```
* 엉성하게 문서화 된 규약에 기대지 않음
* `final` 필드 용법과 충돌하지 않음
* 불필요한 검사 예외나 형변환이 필요치 않음
* 해당 클래스가 구현한 '인터페이스' 타입의 인스턴스를 인수로 받을 수 있음 (변환 생성자, 변환 팩터리 메서드)
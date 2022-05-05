# 인스턴스 수를 통제해야 한다면 readResolve 보다는 열거 타입을 사용하라
### 싱글턴 패턴과 직렬화
```java
// 인스턴스가 하나만 만들어지도록 보장하는 클래스
public class Elvis {
	public static final Elvis INSTANCE = new Elvis();
	private Elvis(){ }
	
	public void leaveTheBuilding() { ... }
}
```
* 이전 싱글턴 예제
* 하지만 위 클래스에 Serializable 를 추가하는 순간 더 이상 싱글턴이 아니게 됨
* 기본 직렬화를 사용하지 않아도, readObject 메서드를 제공하지 않아도 소용없음
### readResolve - 싱글턴 속성 유지 방법
* readObject 메서드가 만든 인스턴스를 다른 것으로 대체할 수 있음
  * 역직렬화 후 새로 생성된 객체를 인수로 readResolve가 호출되고, 해당 메서드가 반환한 객체 참조가 새로 생성된 객체를 대신 반환
```java
private Object readResolve() {
	// 기존에 생성된 인스턴스를 반환
	return INSTANCE;
}
```
* readResolve 메서드를 인스턴스의 통제 목적으로 사용한다면 모든 필드는 `transient`로 선언해야 함
  * 그렇지 않으면 역직렬화 과정에서 역직렬화된 인스턴스를 가져올 수 있어, 싱글턴이 깨지게 됨
### enum : 해결책
* 자바가 선언한 상수 외에 다른 객체가 없음을 보장해줌
```java
public enum Elvis {
	INSTANCE;
	
	// ... 기타 필요 데이터들
}
```
* 직렬화 가능 인스턴스 통제 클래스를 작성해야 할 때, 컴파일 타임에는 어떤 인스턴스들이 있는지 모를 수 있음
  * 이럴 때는 열거 타입으로 표현하는 것이 불가능하기에, readResolve 메서드를 사용할 수밖에 없음
### 결론
* 불변식을 지키기 위해 인스턴스를 통제해야 한다면 가능한 열거 타입을 사용하자.
* 열거 타입 사용이 여의치 않은 상황에 직렬화 및 인스턴스 통제가 필요하다면 readResolve 메서드를 사용하자.
  * 단, 그 클래스의 모든 참조 타입 인스턴스 필드는 transient로 선언한다.
---
[[Prev act >>]](../act4/README.md)  
[[Next act >>]](../act6/README.md)
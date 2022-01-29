# 인터페이스는 타입을 정의하는 용도로만 사용하라
## 인터페이스
* 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할
* 어떤 인터페이스를 구현한다는 것은 자신의 인스턴스로 무엇을 할 수 있는지를 클라이언트에게 알려주는 것
* 인터페이스는 오직 위 내용의 용도로만 사용해야 함
### 상수 인터페이스 안티패턴 == 사용금지
* 상수를 뜻하는 static final 필드로만 가득 찬 인터페이스를 의미
```java
public interface PhysicalConstants {
    static final double AVOGADRO_NUMBER = 6.022_140_857e23;
    static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
    static final double ELECTRON_MASS = 9.109_383_56e-31;
}
```
* 이 상수들을 사용하려는 클래스에서는 정규화된 이름을 쓰는 것을 피하고자 해당 인터페이스 가끔 구현
* **상수 인터페이스 안티패턴은 인터페이스를 잘못 사용한 예**
### 상수가 필요할 경우
* 특정 클래스나 인터페이스와 강하게 연관된 상수라면 그 클래스나 인터페이스 자체에 추가해야 함
* 예) Integer와 Double에 선언된 MIN_VALUE와 MAX_VALUE 상수, 열거 타입
* 아니면 private 생성자를 이용해 인스턴스화를 막아버린 유틸리티 클래스
```java
public class PhysicalConstants {
    private PhysicalConstants() {}
    
    public static final double AVOGADRO_NUMBER = 6.022_140_857e23;
    public static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
}
```
### 결론
인터페이스는 타입을 정의하는 용도로만 사용할 것. 상수 공개 수단으로 사용하지 말아야 함

---
[[Prev act >>]](../act7/README.md)  
[[Next act >>]](../act9/README.md)
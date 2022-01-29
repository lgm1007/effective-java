# clone 재정의는 주의해서 진행하라
### cloneable 인터페이스
* 상속받은 클래스가 복제해도 되는 클래스임을 명시하는 용도
### Object clone 메서드 일반 규약
* `x.clone() != x` 는 true (원본 객체와 복사 객체는 서로 다름)
* `x.clone().getClass() == x.getClass()` 는 true, 하지만 반드시 만족하지 않음
* `x.clone().equals(x)` 는 true, 하지만 필수는 아님
* `x.clone().getClass() == x.getClass()`, `super.clone()` 을 호출해서 얻은 객체를 clone 메서드가 반환하면, 이 식은 true
### clone 재정의 시 주의사항
* clone 은 원본 객체에 아무런 영향을 끼치지 않는 동시에 복사된 객체의 불변식을 보장해야 함
* 복제할 수 있는 클래스를 만들기 위해 일부 필드에서 `final` 한정자를 제거해야 할 수 있음
* 재정의될 수 있는 메서드를 호출하지 않아야 함
* 가변 객체가 포함된 객체의 복사는 조심해야 함
```java
public class CloneableList implements Cloneable {
    private String name;
    private List<String> nameList = new ArrayList<>();
    
    public CloneableList(String name) {
        addName(name);
    }
    
    public void addName(String name) {
        nameList.add(name);
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public List<String> getNameList() {
        return nameList;
    }
    
    // 리스트와 같은 가변 객체가 포함되어 있는 경우
    // super.clone() 메서드의 사용을 조심해야 함
    // 복제된 인스턴스의 가변 객체를 변경하면 원본 객체도 동일하게 변경되버리는 현상 발생!
    @Override
    protected CloneableList clone() throws CloneNotSupportedException {
        return (CloneableList) super.clone();
    }
}
```
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
---
[[Prev act >>]](../act3/README.md)  
[[Next act >>]](../act5/README.md)
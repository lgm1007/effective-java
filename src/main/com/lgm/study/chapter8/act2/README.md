# 적시에 방어적 복사본을 만들어라
### 방어적 프로그래밍
* 자바도 다른 클래스로부터 침범을 아무런 노력 없이 막을 수 있는 건 아님
  * 클라이언트가 불변식을 깨뜨리려한다고 가정하고 방어적으로 프로그래밍해야 함
  * 악의적인 공격으로부터 방어하지 못하거나, 실수로 클래스를 오작동하게 만들 수도 있음
### 기간을 표현한 클래스 - 불변식을 지키지 못함
```java
public final class Period {
    private final Date start;
    private final Date end;

    public Period(Date start, Date end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");
        }
        this.start = start;
        this.end = end;
    }

    public Date start() {
        return start;
    }

    public Date end() {
        return end;
    }
}
```
* Date가 가변이라는 것으로 어렵지 않게 불변식을 깨뜨릴 수 있음
### Period 인스턴스이 내부 공격하기
```java
public final class Period {
    // 코드 생략 ...

    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period period = new Period(start, end);
        end.setYear(78); // period 내부 수정하기
    }
}
```
* 해결법
  * Date 대신 불변인 인스턴스 사용
  * Date는 새로운 코드를 작성할 때엔 더 이상 사용하지 말 것
  * 외부 공격으로부터 인스턴스의 내부를 보호하려면, **생성자에게 받은 가변 매개변수 각각을 방어적으로 복사**해야 함
### 수정한 생성자 - 매개변수의 방어적 복사본 만들기
```java
public final class Period {
    private final Date start;
    private final Date end;

    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");
        }
    }

    // ...
}
```
* 매개변수의 유효성 검사하기 전에 방어적 복사본을 만들고, 해당 복사본으로 유효성을 검사
  * 멀티스레딩 환경이라면 원본 객체를 수정할 위험이 있기 때문에 순서를 위와 같이 함
### Period 인스턴스를 향한 접근자 메서드 공격
```java
public final class Period {

    // 생략 ...

    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period period = new Period(start, end);
        period.end().setYear(78); // period 내부 변경
    }
}
```
* 접근자 메서드가 내부의 가변 정보를 직접 드러내기 때문에 Period 인스턴스는 아직 변경 가능
  * 방어법 : 단순히 접근자가 가변 필드의 방어적 복사본을 반환하면 됨
### 수정한 접근자 - 필드의 방어적 복사본을 반환하기
```java
public final class Period {

    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }
}
```
* 생성자와 달리 접근자 메서드에서는 방어적 복사에 `clone`을 사용해도 됨
  * Period가 가진 Date 객체는 `java.util.Date`임이 확실하기 때문 (신뢰할 수 없는 하위 클래스가 아님)
  * 그래도 인스턴스를 복사하는 데 일반적으로 생성자나 정적 팩토리가 더 나음
* 클래스가 불변이든 가변이든, 가변인 내부 객체를 클라이언트에 반환할 때엔 반드시 심사숙고해야 함
### 방어적 복사의 생략
* 방어적 복사에는 성능 저하가 따르며, 항상 쓸 수 있는 것도 아님
  * 호출자가 컴포넌트 내부를 수정하지 않으리라 확신하면 방어적 복사를 생략할 수 있음
* 다른 패키지에서 사용한다고 해서 넘겨받은 가변 매개변수를 항상 방어적으로 복사해 저장해야 하는 것은 아님
    * 넘겨받는 메서드를 호출하는 클라이언트는 해당 객체를 더 이상 직접 수정하지 않는다고 약속해야 함
### 결론
* 클래스가 클라이언트로부터 받는, 또는 클라이언트로 반환하는 구성요소가 가변이라면 해당 요소는 반드시 방어적으로 복사해야 한다.
* 복사 비용이 너무 크거나 클라이언트가 그 요소를 잘못 수정할 일이 없음을 신뢰한다면 방어적 복사를 수행하는 대신 해당 구성요소를 수정했을 때의 책임이 클라이언트에 있음을 명시해야 한다.
---
[[Prev act >>]](../act1/README.md)  
[[Next act >>]](../act3/README.md)
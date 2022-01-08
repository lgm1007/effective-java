# 추상 클래스보다는 인터페이스를 우선하라
### 추상 클래스와 인터페이스
* 추상 클래스가 정의한 타입을 구현한 클래스는 반드시 추상 클래스의 하위 클래스가 되어야 함
* 인터페이스는 추상 클래스에 비해 자유로움
  * 기존 클래스 위에 추상 클래스를 상속시키기는 어려움
    * 계층 구조상 같은 조상이어야 함
  * 기존 클래스에 인터페이스는 정의해야 하는 메서드를 선언하기만 하면 됨
### 믹스인(mixin)
* 인터페이스는 믹스인 정의에 알맞음
* 믹스인 : 대상 타입의 주된 기능에 선택적 기능을 혼합하는 것
```java
public class File implements Serializable, Comparable<File> {
    ...
}
```
* 추상 클래스의 여러 부모 클래스를 가질 수 없는 클래스의 계층 구조에서는 믹스인을 사용하기 합리적이지 않음
### 계층 구조가 없는 구조
* 인터페이스는 계층구조가 없는 구조를 만들 수 있음
```java
// 가수
public interface Singer {
    AudioClip sing(Song s);
}

// 작곡가
public interface Songwriter {
    Song compose(int chartPosition);
}

// 노래 + 작곡 = 싱어송라이터
public interface SingerSongWriter extends Singer, Songwriter {
    AudioClip strum();
    void actSentensive();
}
```
### 인터페이스 + 추상 골격 구현 클래스
* 인터페이스와 추상 골격 구현 클래스를 함께 제공하여 인터페이스와 추상 클래스 둘의 장점을 갖는 경우도 있음
* 인터페이스로는 타입을 정의하고 필요한 경우 디폴트 메서드도 정의
* 골격 구현 클래스에서 나머지 메서드들 정의
* 주로 템플릿 메서드 패턴 (Template Method Pattern)
* 관례상 컨벤션
  * 이름 XXX 이면, 골격 구현 클래스 이름은 AbstractXXX
  * `AbstractSet`, `AbstractList`, `AbstractMap` 등

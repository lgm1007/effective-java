# 상속을 고려해 설계하고 문서화하라. 그러지 않았으면 상속을 금지하라
### 상속용 클래스 문서화
* 상속용 클래스는 재정의할 수 있는 메서드들을 내부적으로 어떻게 이용하는지 문서로 남겨야 함
* 호출되는 메서드가 재정의 가능한 메서드라면 이를 호출하는 메서드의 API 설명에 적시해야 함
  * 추가) 어떤 순서로 호출되는지, 각각의 호출 결과가 어떤 영향을 주는지 담아야 함
* 좋은 API 문서란 "어떻게"가 아닌 "무엇"을 하는지 설명해야 한다. 라는 말과 대치됨
### 상속용 클래스 테스트
* 상속용 클래스를 테스트하는 방법은 직접 하위 클래스를 만드는 것이 유일
  * 상속용으로 설계한 클래스는 배포 전에 반드시 하위 클래스를 만들어 검증해야 함
### 상속을 허용하는 클래스가 지켜야 할 제약
* 상속용 클래스의 생성자는 직접적이든 간접적이든 재정의 가능 메서드를 호출해서는 안 됨
```java
public class Super {
    public Super() {
        overrideMtd();
    }
    
    public void overrideMtd() {
    }
}

public final class Sub extends Super {
    private final Instant instant;
    
    Sub() {
        instant = Instant.now();
    }
    
    @Override
    public void overrideMtd() {
        System.out.println(instant);
    }
}

// Sub 인스턴스 생성 시 Super의 생성자가 먼저 호출되고, Sub의 생성자가 호출됨
// Super의 overrideMtd(), sub.overrideMtd() 총 2번이 호출됨
// Super의 overrideMtd()는 instant가 초기화되지 않은 상태로 실행되므로 null 발생
// 만일 println 이 아닌 instant를 호출하려 했다면 NullPointerException 던짐
Sub sub = new Sub();
sub.overrideMtd();
```
* `clone`과 `readObject` 모두 직접적으로든 간접적으로든 재정의 가능 메서드를 호출해서는 안 됨
* Serializable을 구현할 때 `readResolve`나 `writeReplace` 메서드는 private가 아닌 protected로 선언함
  * private로 선언하면 하위 클래스에서 무시되어 문제 발생
### 상속 금지
* 위 문제들을 해결하는 가장 좋은 방법 = 상속 금지
* 상속 금지 방법
  1. 클래스를 final로 선언
  2. 모든 생성자를 private나 package-private로 선언하고, public 정적 팩토리를 만들어 줌
     * 내부에서 다양한 하위 클래스를 만들어 사용할 수 있음 = 유연성
### 상속 허용
* 구체 클래스가 표준 인터페이스를 구현하지 않았는데 상속을 금지하면 사용하기 불편해짐
* 상속 허용 방법
  1. 클래스 내부에서는 재정의 가능 메서드를 사용하지 않게 만들고 이를 문서 작성
     * 재정의 가능 메서드를 호출하는 자기 사용 코드를 완벽히 제거
  2. 재정의 가능 메서드를 사용하는 코드를 제거
     * 재정의 가능 메서드는 자신의 본문 코드를 private로 옮기고, 이를 호출하도록 수정
---
[[Prev act >>]](../act4/README.md)  
[[Next act >>]](../act6/README.md)
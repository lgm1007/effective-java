# Comparable을 구현할지 고려하라
### compareTo 메서드
* 아래 두 가지 성격을 제외하면 `Object.equals`와 같음
    * 단순 동치성 비교에 더해 순서까지 비교 가능
    * 제네릭(Generic) 함
      * 데이터 형식에 의존하지 않고, 하나의 값이 여러 다른 데이터 타입을 가질 수 있도록 함
    
* compareTo 의 일반 규약
    * 매개 변수 인스턴스와 자신의 인스턴스를 비교하도록 해야함
    * 자신의 인스턴스가 매개 변수보다 작으면 음수, 같으면 0, 크면 양수를 반환함
    * 모든 x, y에 대해 `sgn(x.compareTo(y)) == -sgn(y.compareTo(x))`여야 함
    * `x.compareTo(y) > 0 && y.compareTo(z) > 0` 이면 `x.compareTo(z) > 0`
    * `x.compareTo(y) == 0` 이면 모든 z에 대해 `sgn(x.compareTo(z)) == sgn(y.compareTo(z))`
* compareTo 메서드 작성 요령
    * Comparable은 타입을 인수로 받는 인터페이스, 따라서 입력 인수의 타입을 확인하거나 형변환할 필요 없음
    * null을 인수로 넣어 호출하면 NullPointerException 을 던져야 함
    * compareTo 메서드는 각 필드가 동치인지 비교하기보다 순서를 비교함
    * Comparable을 구현하지 않은 필드나 표준이 아닌 순서로 비교해야 하면 Comparator 를 대신 사용함
    * compareTo 메서드에서 관계연산자(`<`, `>`) 사용은 추천하지 않음
```java
public final class CaseInsensitiveString implements Comparable<CaseInsensitiveString> {
    // 객체 참조 필드가 하나뿐인 비교자
    public int compareTo(CaseInsensitiveString cis) {
        // 자바 제공 비교자 사용
        return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
    }
}
```
```java
// 클래스에 핵심 필드가 여러 개일 경우 어느 것을 먼저 비교하느냐가 중요
// 똑같지 않은 필드(비교 결과가 0이 아닌)를 찾을 때까지 필드 비교
public int compareTo(PhoneNumber pn) {
    int result = Short.compare(areaCode, pn.areaCode);  // 가장 중요한 코드
    if (result == 0) {
        result = Short.compare(prefix, pn.prefix);      // 두 번째로 중요한 필드
        if (result == 0) {
            result = Short.compare(lineNum, pn.lineNum); // 세 번째로 중요한 필드
        }
    }
    return result;
}
```
```java
// 비교자 생성 메서드를 활용한 비교자
private static final Comparator<PhoneNumber> COMPARATOR = comparingInt((PhoneNumber pn) -> pn.areaCode)
                                                            .thenComparingInt(pn -> pn.prefix)
                                                            .thenComparingInt(pn -> pn.lineNum);

public int compareTo(PhoneNumber pn) {
    return COMPARATOR.compare(this, pn);
}
```
---
[[Prev act >>]](../act4/README.md)  
[[Next act >>]](../../chapter4/act1/README.md)
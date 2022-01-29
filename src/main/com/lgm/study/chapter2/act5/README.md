# 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
### 사용하는 자원에 따라 동작이 달라지는 클래스에는 정적 유틸리티 클래스나 싱글턴 방식이 적합하지 않음
```java
// 부적절한 static 유틸리티 클래스 사용
public class SpellChecker {
    private static final Lexion dictionary = new KoreanDictionary();
    
    // 인스턴스화 방지
    private SpellChecker() {
    }
    
    public static boolean isValid(String word) {
        throw new UnsupportedOperationException();
    }
    
    public static List<String> suggestions(String typo) {
        throw new UnsupportedOperationException();
    }
}

interface Lexion {}
class KoreanDictionary implements Lexion {}
```
```java
// 부적절한 싱글턴 사용
public class SpellChecker {
    private final Lexion dictionary = new KoreanDictionary();
    
    private SpellChecker() {
    }
    
    public static final SpellChecker INSTANCE = new SpellChecker() {
    };

    public static boolean isValid(String word) {
        throw new UnsupportedOperationException();
    }

    public static List<String> suggestions(String typo) {
        throw new UnsupportedOperationException();
    }
}
```
### 위 두 가지 경우 문제점
1. 한국어가 아닌 영어에 대한 SpellChecker로 바꾸고 싶을 때
2. 테스트를 위한 dictionary를 따로 사용하고 싶을 때 
   
-> 위 문제를 위해 SpellChecker 구현을 변경해야 함
### 사용하는 자원에 따라 동작이 달라지는 클래스에는 의존 객체 주입 방식을 사용해야 함
* 인스턴스 생성 시, 생성자에 필요한 자원을 넘겨주기 (의존 객체 주입의 한 형태)
* 의존 객체 주입은 클래스의 유연성, 재사용성, 테스트 용이성을 개선해준다.
* 의존성이 많은 큰 프로젝트에서는 대거(Dagger), 주스(Guice), 스프링(Spring) 등 프레임워크를 사용
---
[[Prev act >>]](../act4/README.md)  
[[Next act >>]](../act6/README.md)
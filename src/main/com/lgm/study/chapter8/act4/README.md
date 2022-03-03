# 다중정의는 신중히 사용하라
### 다중정의(Overloading)
* 이름만 같은 메서드가 매개변수 타입이나 개수만 다르게 갖는 형태
```java
public class CollectionClassifier {
    public static String classify(Set<?> s) {
        return "집합";
    }

    public static String classify(List<?> lst) {
        return "리스트";
    }

    public static String classify(Collection<?> c) {
        return "그 외";
    }
    
    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };
        
        for (Collection<?> c : collections) {
            System.out.println(classify(c));
        }
    }
}
```
* 위 코드의 `classify()` 메서드는 잘 동작할 것 같지만 "그 외"만 출력됨
* for문 안에 `Collection<?> c`는 런타임에 타입이 결정됨. 
    * 즉, 컴파일 타임에는 항상 `Collection<?>` 타입임
* 컴파일 타임에 어떤 `classify()` 메서드가 호출될 것인지 결정되기에 `classify(Collection<?>)` 메서드만 3번 호출되는 것
* **재정의한 메서드는 동적으로 선택되고, 다중정의한 메서드는 정적으로 선택됨**
### 재정의(Overriding)
* 상위 클래스의 메서드를 하위 클래스에서 재정의하는 것
* 재정의한 메서드는 런타임에 어떤 메서드를 호출할지 정해짐
```java
class Wine {
    String name() { return "포도주"; }
}

class SparklingWine extends Wine {
    @Override String name() { return "발포성 포도주"; }
}

class Shampagne extends SparklingWine {
    @Override String name() { return "샴페인"; }
}

public class OverridingTest {
    public static void main(String[] args) {
        List<Wine> wineList = List.of(
          new Wine(), new SparklingWine(), new Shampagne()      
        );
        
        for (Wine wine : wineList) {
            System.out.println(wine.name());
        }
    }
}
```
* 위 코드 실행 시 "포도주", "발포성 포도주", "샴페인" 순서대로 출력
* 컴파일 타임 타입이 모두 Wine인 것과는 무관하게 항상 **가장 하위에서 정의한 재정의 메서드**가 실행됨
    * 의도한 대로 직관과 일치
### 다중정의가 혼동을 일으키는 상황
* API 사용자가 매개변수를 넘길 때, 어떤 다중정의가 호출될지 모른다면 오작동하기 쉬움
* 헷갈릴 수 있는 코드는 작성하지 않음
* 안전하고 보수적으로 진행하려면 매개변수 수가 같은 다중정의는 작성하지 않음
* 가변 인수를 매개변수로 사용한다면 다중정의를 사용하지 않음
### 생성자 다중정의
* 생성자는 이름을 달리 할 수 없으므로 두 번째 생성자부터는 무조건 다중정의가 됨
    * 대안으로 정적 팩토리 메서드가 적절
* 생성자는 재정의될 수 없으므로 다중정의와 혼용될 걱정 없음
* 하지만 여러 생성자가 같은 수의 매개변수를 받아야 하는 경우는 피할 수 없음
    * 매개변수 수가 같은 다중정의 메서드가 많더라도 근본적으로 다르다면 헷갈릴 일이 없음
        * 두 타입의 값을 어느 쪽으로든 형변환할 수 없다는 의미
### 결론
* 일반적으로 매개변수 수가 같을 때는 다중정의를 피하는 게 좋다.
* 만약 다중정의를 피할 수 없는 상황이라면 헷갈릴 만한 매개변수는 형변환하여 정확한 다중정의 메서드가 선택되게끔 한다.
---
[[Prev act >>]](../act3/README.md)  
[[Next act >>]](../act5/README.md)
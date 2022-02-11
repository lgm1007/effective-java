# @Override 어노테이션을 일관되게 사용하라
### @Override 어노테이션
* 상위 타입의 메서드를 재정의했다는 의미
* 메서드 선언에만 사용
### @Override 사용 안할 시 일어날 수 있는 버그
```java
public class Bigram {
    private final char first;
    private final char second;

    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Bigram bigram) {
        return bigram.first == first && bigram.second == second;
    }

    public int hashCode() {
        return 31 * first + second;
    }

    public static void main(String[] args) {
        Set<Bigram> s = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                s.add(new Bigram(ch, ch));
            }
        }
        System.out.println(s.size());
    }
}
```
* Set은 중복을 허용하지 않으니 26이 출력될 것 같지만 실제로는 260이 출력됨
* `equals`를 재정의(Override)하지 않고 다중 정의(Overloading) 했기 때문
    * Object를 재정의하려면 매개변수 타입을 Object로 해야만 함
    * 위 코드는 별개의 `equals` 메서드를 정의한 것
### @Override를 일관성 있게 사용하자
* @Override를 사용하면 컴파일 오류로 사고를 방지할 수 있음
* 즉, 상위 클래스의 메서드를 재정의하려는 모든 메서드에 @Override 어노테이션을 달도록 해야 함
  * 구체 클래스에서 상위 클래스의 추상 메서드를 재정의할 때는 굳이 @Override를 달지 않아도 됨
* @Override는 클래스뿐 아니라 인터페이스의 메서드를 재정의할 때도 사용 가능
### 결론
* 재정의한 모든 메서드에 @Override 어노테이션을 의식적으로 달면 실수했을 때 컴파일러가 알려줄 것이다.
* 예외는 한 가지뿐, 구체 클래스에서 상위 클래스의 추상 메서드를 재정의할 때는 달지 않아도 된다.
* 의식적으로 일관성 있게 달도록 하자.
---
[[Prev act >>]](../act6/README.md)  
[[Next act >>]](../act8/README.md)
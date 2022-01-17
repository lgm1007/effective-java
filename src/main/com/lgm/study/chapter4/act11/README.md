# 톱레벨 클래스는 한 파일에 하나만 담으라
### 두 클래스가 한 파일에 정의됨 = 따라하지 말 것!
```java
public class Main {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }
}

class Utensil {
    static final String NAME = "Pan";
}

class Dessert {
    static final String NAME = "Cake";
}
```
그 다음 Dessert.java 파일을 다음과 같이 작성했을 경우
```java
class Utensil {
    static final String NAME = "Apple";
}

class Dessert {
    static final String NAME = "Pie";
}
```
* javac Main.java Dessert.java 명령으로 컴파일 시 컴파일 오류가 나며 Utensil, Dessert 클래스를 중복 정의했다고 알려줄 것
* javac Main.java나 javac Main.java Utensil.java 명령으로 컴파일하면 PanCake 출력
* javac Dessert.java Main.java 명령으로 컴파일하면 ApplePie 출력
* 즉, 컴파일러에 어느 소스파일을 먼저 건네느냐에 따라 동작이 달라지는 문제 발생
* 톱레벨 클래스들을(Utensil, Dessert) 서로 다른 파일로 정의하면 해결 가능
  * 굳이 한 파일에 담고 싶다면 정적 멤버 클래스를 사용하는 방법 고려
  * 다른 클래스의 부차적인 클래스라면 정적 멤버 클래스가 나음
```java
public class Main {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }
    
    private static class Utensil {
        static final String NAME = "Pan";
    }
    
    private static class Dessert {
        static final String NAME = "Cake";
    }
}
```
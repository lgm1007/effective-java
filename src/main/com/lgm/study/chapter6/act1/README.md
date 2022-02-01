# int 상수 대신 열거 타입을 사용하라
* 자바에서 Enum 타입을 지원하기 전에는 아래와 같이 정수 상수를 묶음으로 선언하여 사용함 (정수 열거 패턴)
```java
public static final int APPLE_FUJI = 0;
public static final int APPLE_PIPPIN = 1;
public static final int APPLE_GRANNY_SMITH = 2;

public static final int ORANGE_NAVEL = 0;
public static final int ORANGE_TEMPLE = 1;
public static final int ORANGE_BLOOD = 2;
```
### 정수 열거 패턴의 단점
1. 타입 안전을 보장할 방법이 없으며 표현력이 좋지 않음
    * 위 코드에서 사과용 상수들은 `APPLE_`, 오렌지용 상수들은 `ORANGE_` 로 시작함
    * `APPLE_FUJI`와 `ORANGE_NAVEL`은 모두 0을 의미하기 때문에 `APPLE_FUJI`가 들어가야 하는 곳에 `ORANGE_NAVEL`이 들어가도 컴파일때 문제 없음
    * 자바가 정수 열거 패턴을 위한 별도 이름 공간을 지원하지 않음
2. 정수 열거 패턴을 사용한 프로그램은 깨지기 쉬움
    * 평범한 상수를 나열한 것뿐이라면 해당 값이 클라이언트 파일에 그대로 새겨짐, 만약 상수의 값이 바뀌면 반드시 다시 컴파일해야 함
3. 정수 상수는 문자열로 출력하기 어려움
```java
public static final int APPLE_FUJI = 0;
System.out.println(APPLE_FUJI); // APPLE_FUJI 가 아닌 0 출력됨
```
4. 같은 정수 열거 그룹에 속한 모든 상수를 한 바퀴 순회하는 방법도 마땅치 않음
* 위와 같은 단점들을 보완하고 여러 장점들을 제공하기 위한 대안이 열거 타입(Enum Type)이다.
### 열거 타입
* 열거 타입 자체는 클래스
* 열거 상수 하나 당 자신의 인스턴스를 하나씩 만들어 `public static final` 필드로 공개
* 열거 타입은 밖에서 접근할 수 있는 생성자를 제공하지 않음
### 열거 타입의 장점
1. 열거 타입 선언으로 만들어진 인스턴스들은 딱 하나씩만 존재함
    * 클라이언트가 인스턴스를 직접 생성하거나 확장할 수 없음
2. 열거 타입은 컴파일타임 타입 안정성을 제공
```java
public enum Apple {FUJI, PIPPIN, GRANNY_SMITH}
public enum Orange {NAVEL, TEMPLE, BLOOD}
```
 위 코드에서 Apple의 열거 타입을 매개변수로 받는 메서드를 선언했다면, 건네받은 참조는 Apple의 세 가지 값 중 하나임이 확실함  
 다른 타입의 값을 넘기려 하면 컴파일 오류
3. 각자의 이름 공간이 존재
    * 열거 타입에 새로운 상수를 추가하거나 순서를 바꿔도 다시 컴파일하지 않아도 됨
4. 문자열로 출력하기 적합함
    * `toString()` 사용으로 출력 가능
5. 임의의 메서드, 필드를 추가하고 임의의 인터페이스를 구현할 수도 있음
    * Object 메서드들, Comparable 및 Serializable을 구현해 놓음  
* 예제
```java
public enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),
    MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN(5.685e+26, 6.027e7),
    URANUS(8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.447e7);
    
    private final double mass;
    private final double radius;
    private final double surfaceGravity;
    
    private static final double G = 6.67300E-11;
    
    // 생성자
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        this.surfaceGravity = G * mass / (radius * radius);
    }
    
    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;
    }
}
```
### 지원하는 메서드
* `values()` : 자신 안에 정의딘 상수들의 값을 배열에 담아 반환하는 정적 메서드, 값은 선언된 순서로 저장
* `valueOf()` : 상수 이름을 입력받아 그 이름에 해당하는 상수를 반환
* `toString()` : 상수 이름을 문자열로 반환, 원하는 대로 재정의 가능
* `fromString()` : `toString`이 반환하는 문자열을 해당 열거 타입 상수로 반환
### 상수마다 동작이 다르게
* 사칙연산 계산기의 연산 종류를 열거 타입으로 정의하고 실제 연산까지 열거 타입 상수가 직접 수행하기로 함
```java
public enum Operation {
    PLUS, MINUS, TIMES, DIVIDE
}
```
#### switch 문으로 분기처리
```java
public double apply(double x, double y) {
    switch(this) {
        case PLUS: return x + y;
        case MINUS: return x - y;
        case TIMES: return x * y;
        case DIVIDE: return x / y;
    }
    throw new AssertionError("알 수 없는 연산: " + this);
}
```
* 단점 : 깨지기 쉬운 코드
  * 새로운 상수를 추가하면 case문도 새로 추가해야 함
* 기존 열거 타입에 상수별 동작을 혼합해서 넣을 때는 좋은 방법
#### apply() 추상 메서드 선언
```java
public enum Operation {
    PLUS {public double apply(double x, double y) {return x + y;}},
    MINUS {public double apply(double x, double y) {return x + y;}},
    TIMES {public double apply(double x, double y) {return x + y;}},
    DIVIDE {public double apply(double x, double y) {return x + y;}};
    
    public abstract double apply(double x, double y);
}
```
* `apply()`가 추상 메서드라 재정의하지 않았다면 컴파일 오류로 알려줌
#### 상수별 매서드 구현
```java
public enum Operation {
    PLUS("+") {
        public double apply(double x, double y) {return x + y;}
    }
    MINUS("-") {
        public double apply(double x, double y) {return x - y;}
    }
    TIMES("*") {
        public double apply(double x, double y) {return x * y;}
    }
    DIVIDE("/") {
        public double apply(double x, double y) {return x / y;}
    };
    
    private final String symbol;
    
    Operation(String symbol) {this.symbol = symbol;}
    
    @Override public String toString() {return symbol;}
    public abstract double apply(double x, double y);
}
```
### 결론
* 열거 타입은 정수 상수보다 읽기 쉽고, 안전하고 강력함
* 열거 타입은 명시적 생성자나 메서드 없이 쓰이지만, 각 상수를 특정 데이터와 연결짓거나 상수마다 다르게 동작하게 할 때 필요함
* 가끔 하나의 메서드가 상수별로 다르게 동작해야 할 때가 있음
  * 이는 열거 타입에서는 switch문 대신 상수별 메서드 구현을 사용
---
[[Prev act >>]](../../chapter5/act8/README.md)  
[[Next act >>]](../act2/README.md)
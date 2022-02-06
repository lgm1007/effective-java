# 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라
* 열거 타입은 확장할 수 없지만, 연산 코드(operation code)에는 확장할 수 있는 열거 타입이 어울림
* 열거 타입은 임의의 인터페이스를 구현할 수 있음
  * 인터페이스로 확장과 비슷한 효과를 낼 수 있음
* 열거 타입이 표준 인터페이스의 표준 구현체 역할 수행
### 인터페이스를 이용해 확장 가능 열거 타입을 흉내낸다
```java
public enum BasicOperation implements Operation {
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
```
* 열거 타입인 `BasicOperation`은 확장할 수 없지만 인터페이스인 `Operation`은 확장 가능
* `Operation`을 구현한 또 다른 열거 타입을 정의해 기본 타입인 `BasicOperation`을 대체할 수 있음
### 확장 가능한 열거 타입
```java
public enum ExtendedOperation implements Operation {
    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
```
* 지수 연산(EXP)과 나머지 연산(REMAINDER)을 추가한 것
  * `Operation` 인터페이스를 구현한 열거 타입을 작성하기만 하면 됨
* 새로 작성한 연산은 기존 연산을 쓰는 곳이면 어디든지 쓸 수 있음
  * `Operation` 인터페이스를 사용하도록 작성되어 있기만 하면 됨
* apply가 인터페이스 `Operation`에 선언되어 있으니 열거 타입에 따로 추상 메서드로 선언하지 않아도 됨
### 타입 수준에서의 확장된 열거 타입
* 개별 인스턴스 수준에서뿐 아니라, 타입 수준에서도 기본 열거 타입 대신 확장된 열거 타입을 사용할 수 있음
#### 타입 수준에서 확장된 열거 타입 1
* 첫 번째 방법, **Class 객체를 넘기는 것**
```java
public enum ExtendedOperation implements Operation {
    ...

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(ExtendedOperation.class, x, y);
    }

    private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
        for (Operation operation : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, operation, y, operation.apply(x, y));
        }
    }
}
```
* main 메서드는 test 메서드에 `ExtendedOperation`의 class 객체를 넘겨 확장된 연산들이 무엇인지 알려줌
  * `<T extends Enum<T> & Operation> Class<T>`는 Class 객체가 열거 타입인 동시에 `Operation`의 하위 타입이어야 한다는 뜻
  * 열거 타입이어야 원소를 순회할 수 있고, `Operation`이어야 원소가 뜻하는 연산을 수행할 수 있기 때문
#### 타입 수준에서 확장된 열거 타입 2
* 두 번째 방법, **Class 객체 대신 한정적 와일드카드 타입인 `Collection<? extends Operation>`을 넘기는 것**
```java
public enum ExtendedOperation implements Operation {
    ...

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    private static void test(Collection<? extends Operation> operations, double x, double y) {
        for (Operation operation : operations) {
            System.out.printf("%f %s %f = %f%n", x, operation, y, operation.apply(x, y));
        }
    }
}
```
* test 메서드에서 여러 구현 타입의 연산을 조합해 호출할 수 있게 됨
* 하지만 특정 연산에서는 `EnumSet`과 `EnumMap`을 사용할 수 없음
### 인터페이스를 이용한 확장된 열거 타입의 문제점
* 열거 타입끼리는 구현을 상속할 수 없음
### 결론
* 열거 타입 자체는 확장할 수 없다.
* **인터페이스와 그 인터페이스를 구현하는 기본 열거 타입을 함께 사용해** 같은 효과를 낼 수 있다.
  * 위 방법으로 클라이언트는 인터페이스를 구현해 자신만의 열거 타입 또는 다른 타입을 만들 수 있다.
* 만약 API가 열거 타입을 직접 명시하지 않고 인터페이스 기반으로 작성되었다면 기본 열거 타입의 인스턴스가 쓰이는 모든 곳을 새로 확장한 열거 타입의 인스턴스로 대체해 사용할 수 있다.
---
[[Prev act >>]](../act4/README.md)  
[[Next act >>]](../act6/README.md)
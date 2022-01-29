# 태그 달린 클래스보다 클래스 계층구조를 활용하라
### 태그 달린 클래스
* 현재 표현하는 의미를 태그 값으로 알려주는 클래스
```java
class Figure {
    enum Shape { RECTANGLE, CIRCLE };
    
    // 태그 필드 : 현재 모양을 나타냄
    final Shape shape;
    
    // RECTANGLE 에서 사용하는 필드
    double length;
    double width;
    
    // CIRCLE 에서 사용하는 필드
    double radius;
    
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }
    
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }
    
    double area() {
        switch (shape) {
            case CIRCLE:
                return Math.PI * (radius * radius);
            case RECTANGLE:
                return length * width;
            default:
                throw new AssertionError(shape);
        }
    }
}
```
### 태그 달린 클래스의 단점
* 열거 타입 선언, 태그 필드, switch 문 등 쓸데없는 코드가 많음
* 여러 구현이 한 클래스에 혼합되어 있어 가독성이 나쁨
* 다른 의미를 위한 코드도 언제나 함께하기 때문에 메모리를 많이 사용함
* 필드들을 final로 선언하려면 해당 의미에 쓰이지 않는 필드들까지 생성자에서 초기화해야 함
* 다른 의미를 추가하려면 코드 수정이 있어야 함
* 결론 : 태그 달린 클래스는 **장황하고, 오류를 내기 쉽고, 비효율적**임
### 위 문제 해결 방법
* 클래스 계층구조를 사용하는 subtyping 사용
  * 계층 구조의 루트(root)가 될 추상 클래스를 정의하고, 태그 값에 따라 동작이 달라지는 메서드들을 root 클래스의 추상 메서드로 선언
  * 태그 값에 상관없이 동작이 일정한 메서드들을 루트 클래스에 일반 메서드로 추가
  * 모든 하위 클래스에서 공통으로 사용하는 데이터 필드들도 전부 루트 클래스로 추가
  * 루트 클래스를 확장한 구체 클래스를 의미별로 하나씩 정의
```java
// 루트 추상 클래스
abstract class Figure {
    abstract double area();
}

// Circle
class Circle extends Figure {
    final double radius;
    
    Circle (double radius) {
        this.radius = radius;
    }
    
    @Override
    double area() {
        return Math.PI * (radius * radius);
    }
}

// Rectangle
class Rectangle extends Figure {
    final double length;
    final double width;
    
    Rectangle (double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    double area() {
        return length * width;
    }
}
```
### 클래스 계층 구조 이용의 장점
* 간결하고 명확함
* 각 의미를 독립된 클래스로 담아 관련 없는 데이터 필드들 제거
* 각 클래스의 생성자가 모든 필드를 초기화하였으며, 추상 메서드들을 모두 구현하였는지 컴파일러 측에서 확인해 줌
* 루트 클래스의 코드를 건드리지 않고 다른 프로그램이 독립적으로 계층구조를 확장 및 이용 가능
* 타입이 의미별로 존재해 변수의 의미를 명시하거나 제한할 수 있고, 특정 의미만 매개변수로 받을 수 있음
* 타입 사이의 계층 관계를 반영할 수 있어 유연성, 컴파일 타임 검사 능력을 높임
---
[[Prev act >>]](../act8/README.md)  
[[Next act >>]](../act10/README.md)
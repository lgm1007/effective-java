# 멤버 클래스는 되도록 static 으로 만들어라
### 중첩 클래스
* 다른 클래스 안에 정의된 클래스를 의미
* 자신을 감싼 바깥 클래스에서만 쓰이며, 그 외에 쓰임새가 있다면 톱레벨 클래스로 만들어야 함
* 종류
  * 정적 멤버 클래스
    * 다른 클래스 안에서 선언되고, 바깥 클래스의 private 멤버에도 접근 가능하다는 점을 제외하고 일반 클래스와 동일
    ```java
    public class OuterClass {
        private int x = 10;
        
        private static class InnerClass {
            void test() {
                OuterClass outerClass = new OuterClass();
                // private 멤버에 접근
                outerClass.x = 100;
            }
        }  
    }
    ```
    * 흔히 바깥 클래스와 함께 쓰일 때만 유용한 private 도우미 클래스로 사용됨
  * 비정적 멤버 클래스
    * 비정적 멤버 클래스의 인스턴스는 바깥 클래스의 인스턴스와 암묵적으로 연결됨
    * 따라서 인스턴스 메서드에서 정규화된 `this`를 사용해 바깥 인스턴스의 메서드를 호출하거나 바깥 인스턴스의 참조를 가져올 수 있음
    * 비정적 멤버 클래스의 인스턴스와 바깥 인스턴스 사이의 관계는 인스턴스화될 때 확립되며 변경 불가능
    * `어댑터`를 정의할 때 자주 쓰임 (어떤 클래스의 인스턴스를 감싸 마치 다른 클래스의 인스턴스처럼 보이게 하는 뷰)
     ```java
     import java.util.Iterator;public class MySet<E> extends AbstractSet<E> {
        ...
    
        @Override
        public Iterator<E> iterator() {
            return new MyIterator();
        }
    
        private class MyIterator implements Iterator<E> {
            ...
        }
    }
     ```
    * 멤버 클래스에서 바깥 인스턴스에 접근할 일이 없다면 static을 붙여 정적 멤버 클래스로 만들 것
  * 익명 클래스
    * 멤버와 달리, 쓰이는 시점에 선언과 동시에 인슽너스가 만들어짐
    * 오직 `비정적인` 문맥에서 사용될 때만 바깥 클래스의 인스턴스를 참조할 수 있음
    * `정적` 문맥 에서라도 상수 변수 이외의 정적 멤버는 가질 수 없음
    * 상수 표현을 위해 초기화된 `final` 기본 타입과 문자열 필드만 가질 수 있음
    ```java
    public class AnonymousExample {
        private double x;
        private double y;
    
        public double operator() {
            Operator operator = new Operator() {
                @Override
                public double plus() {
                    return x + y;
                }
    
                @Override
                public double minus() {
                    return x - y;
                }
            };
            return operator.plus();
        } 
    }
    
    interface Operator {
        double plus();
        double minus();
    }
    ```
    * 익명 클래스를 사용하는 클라이언트는 그 익명 클래스가 상위 타입에서 상속한 멤버 외에는 호출할 수 없음
    * 익명 클래스의 또 다른 쓰임은 `정적 팩터리 메서드`를 구현할 때
  * 지역 클래스
    * 지역 변수를 선언할 수 있는 곳이면 실질적으로 어디서든 선언 가능, 유효 범위도 지역 변수와 같음
    * 멤버 클래스처럼 이름이 있고 반복해서 사용 가능
    * 익명 클래스처럼 `비정적` 문맥에서 사용될 때만 바깥 인스턴스를 참조할 수 있음
    * `정적 멤버`는 가질 수 없으며, 가독성을 위해 짧게 작성해야 함

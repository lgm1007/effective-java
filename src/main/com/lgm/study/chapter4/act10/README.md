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
  * 지역 클래스

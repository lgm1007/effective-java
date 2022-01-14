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
  * (비정적) 멤버 클래스
  * 익명 클래스
  * 지역 클래스

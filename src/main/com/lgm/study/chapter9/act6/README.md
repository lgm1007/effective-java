# 다른 타입이 적절하다면 문자열 사용을 피하라
### 문자열(String)을 사용하지 말아야 할 경우
1. 다른 값의 타입을 대신하기에 적합하지 않음
    * 많은 사람들이 파일, 네트워크, 키보드 입력으로부터 데이터를 받을 때 주로 문자열을 사용함
    * 입력받을 데이터가 진짜 문자열일 경우에만 적합함
    * 받은 데이터가 수치형이면 int, float, BigInteger 등 적당한 수치 타입으로 변환해야 함
2. 열거 타입을 대신하기에 적합하지 않음
    * 상수를 열거할 때는 문자열보단 열거 타입이 좋음
3. 문자열은 혼합 타입을 대신하기에 적합하지 않음
    * 여러 요소가 혼합된 데이터를 하나의 문자열로 표현하는 것은 대체로 좋지 않음
    * 혼합 타입을 문자열로 처리한 부적절한 예
    ```java
    String compoundKey = className = "#" + i.next();
    ```
    * 혹시라도 두 요소를 구분해주는 "#"이 두 요소 중 하나에서 쓰였다면 혼란스러운 결과를 초래할 것
    * 차라리 전용 클래스를 새로 만드는 편이 나음
        * 이런 경우 보통 private 정적 멤버 클래스로 선언함
4. 권한을 표현하기에 적합하지 않음
    * 문자열로 권한을 구분한 잘못된 예
    ```java
   public class ThreadLocal {
        private ThreadLocal() {} // 인스턴스 생성 방지
    
        public static void set(String key, Object value);
   
        public static Object get(String key);
   }
    ```
   * 위 코드의 문제는 스레드 구분용 문자열 키가 전역 이름공간에 공유된다는 점
   * 의도한 대로 동작하려면 각 클라이언트가 고유한 키를 제공해야 함
   * 의도치 않게 동일한 키를 사용하는 클라이언트들이 생기면 해당 클라이언트 모두 기능이 재대로 동작하지 않을 것
   * 리팩토링한 코드 - Key를 ThreadLocal로 변경 및 매개변수화하여 타입안전성 확보
    ```java
   public final class ThreadLocal<T> {
        public ThreadLocal();
        public void set(T value);
        public T get();
   }
    ```
### 결론
* 문자열은 받을 데이터가 진짜 문자열일 경우에만 사용하는 편이 좋다.
* 문자열은 잘못 사용하면 번거롭고, 덜 유연하고, 느리며, 오류 가능성도 크다.
---
[[Prev act >>]](../act5/README.md)  
[[Next act >>]](../act7/README.md)
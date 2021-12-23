# 불필요한 객체 생성을 피하라
```java
// new 연산자를 사용하여 String 생성 시 항상 객체를 생성함
String s = new String("java");

// 아래는 새로운 인스턴스 생성 대신 하나의 String 인스턴스를 재사용
String ss = "java";
```
### 정적 팩터리 메서드를 제공하는 불변 클래스
### 생성 비용이 비싼 객체는 캐싱하여 재사용
```java
// String.matches 메서드 내부에서 만드는 Pattern는 인스턴스 생성 비용이 높은 객체
static boolean isPhoneNumber(String s) {
    return s.matches("^\d{3}-\d{3,4}-\d{4}$");    
}
```
```java
public class PhoneNumberals {
    // 개선 : 비싼 객체를 클래스 초기화 과정에서 직접 생성하여 캐싱해두고 재사용
    private static final Pattern PHONE = Pattern.compile(
            "^\d{3}-\d{3,4}-\d{4}$"
    );
    
    static boolean isPhoneNumber(String s) {
        return PHONE.matcher(s).matches();
    }
} 
```
### 어댑터 패턴 사용
### 오토 박싱(auto boxing)
* 오토박싱 : 기본 타입과 박싱된 기본 타입을 섞어 사용할 때 자동으로 변환해주는 기술
* 기본타입과 그에 대응하는 박싱된 기본타입의 구분을 흐려주지만 완전히 없애진 않아, 성능에서 좋아진다고 볼 수 없음
* 박싱된 기본타입보단 기본 타입을 사용 권장
```java
// sum 변수를 long이 아닌 Long 을 사용
// 불필요한 Long 인스턴스가 반복 수만큼 생성됨
public static long sum() {
    Long sum = 0L;
    for(long i = 0; i <= INTEGER.MAX_VALUE; i++) {
        sum += i;    
    }
    return sum;
}
```
### 자신만의 객체 풀을 만들지 않기
* 일반적으로 자체 풀은 코드를 헷갈리게 하고 메모리 사용량을 늘려 성능을 떨어뜨림
### 오해 - 객체 생성은 피해야한다?
* JVM은 별일 없는 작은 객체의 생성 및 회수에는 크게 부담되지 않음
* 명확성, 기능 등을 위해 객체를 생성하는 일은 일반적으로 좋은 방법
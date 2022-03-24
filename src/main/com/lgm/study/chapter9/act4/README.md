# 정확한 답이 필요하다면 float와 double은 피하라
* float와 double 타입은 공학 계산용으로 설계됨
* 넓은 범위의 수를 빠르게 정밀한 `근사치`로 계산하도록 세심히 설계됨
* 금융 관련 계산과는 맞지 않음
### 금융 계산에는 BigDecimal, int 혹은 long을 사용하라
```java
public static void main(String[] args) {
    final BigDecimal TEN_CENTS = new BigDecimal(".10");

    int itemsBought = 0;
    BigDecimal funds = new BigDecimal("1.00");
    for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
        funds = funds.subtract(price);
        itemsBought++;
    }
    System.out.println(itemsBought + "개 구입");
    System.out.println("잔돈(달러): " + funds);
}
```
* BigDecimal의 단점
    * 기본 타입보다 쓰기 불편하며, 훨씬 느림
    * 단발성이라면 느리다는 문제는 무시할 수 있음
* BigDecimal의 대안으로 int 또는 long 타입 사용
    * int와 long을 사용하여 성능 향상은 가능하나 다룰 수 있는 값의 크기가 제한되며, 소수점을 직접 관리해야 하는 단점 존재
* 위 코드를 달러 대신 센트로 계산한 코드
```java
public static void main(String[] args) {
    int itemsBought = 0;
    int funds = 100;
    for (int price = 10; funds >= price; price += 10) {
        funds -= price;
        itemsBought++;
    }
    System.out.println(itemsBought + "개 구입");
    System.out.println("잔돈(센트): " + funds);
}
```
### 결론
* 정확한 답이 필요한 계산에는 float나 double 타입은 피하라.
* 소수점 추정은 시스템에 맞기고, 사용 시 불편함이나 성능 저하를 신경쓰지 않으면 BigDecimal을 사용하라.
    * BigDecimal이 제공하는 반올림 모드를 사용하면 반올림을 완벽히 제어할 수 있다.
* 소수점 추정을 직접 추적할 수 있고 성능이 중요하며 숫자가 너무 크지 않다면 int나 long을 사용하라.
    * 아홉 자리 십진수로 표현 가능하다면 int
    * 열여덟 자리 십진수로 표현 가능하다면 long
    * 열여덟 자리를 넘어가면 BigDecimal을 사용해야 한다.
---
[[Prev act >>]](../act3/README.md)  
[[Next act >>]](../act5/README.md)
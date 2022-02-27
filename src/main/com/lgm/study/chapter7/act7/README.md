# 스트림 병렬화는 주의해서 적용하라
* 동시성 프로그래밍을 작성할 때는 안전성과 응답 가능(liveness) 상태를 유지해야 함
### 병렬 수행 효율
#### 스트림을 사용한 첫 20개의 메르센 소수 생성 프로그램
```java
public class Mersenne {
    public static void main(String[] args) {
        primes().map(prime -> TWO.pow(prime.intValue()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);
    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }
}
```
* 환경이 아무리 좋아도 데이터 소스가 `Stream.iterate`거나 중간 연산으로 limit를 쓰면 파이프라인 병렬화로는 성능 개선을 기대할 수 없음
* 대체로 스트림의 소스가 `ArrayList`, `HashMap`, `HashSet`, `ConcurrentHashMap`의 인스턴스거나 배열, int 범위, long 범위일 때 병렬화의 효과가 좋음
#### 참조 지역성
* 참조 지역성이 낮으면 스레드는 데이터가 주 메모리에서 캐시 메모리로 전송되어 오기를 기다림
  * 참조 지역성은 다량의 데이터를 처리하는 벌크 연산을 병렬화할 때 아주 중요한 요소로 작용함
* 참조 지역성이 가장 뛰어난 자료구조로는 **기본 타입의 배열**
  * 기본 타입 배열에서는 참조가 아닌 데이터 자체가 연속해서 저장되기 때문
#### 스트림 파이프라인 종단 연산
* 종단 연산 중 병렬화에 가장 적합한 것은 **축소(reduction)**
  * 축소 : 파이프라인에서 만들어진 모든 원소를 하나로 합치는 작업
* `anyMatch`, `allMatch`, `noneMatch`처럼 조건에 맞으면 바로 반환되는 메서드도 병렬화에 적합
* 가변 축소(mutable reduction)를 수행하는 Stream의 collect 메서드는 컬렉션들을 합치는 부담이 크기 때문에 병렬화에 적합하지 않음
#### 성능 저하 및 안전 실패
* 스트림을 잘못 병렬화하면 응답 불가 상태에 빠지거나 성능이 나빠지며, 결과 자체가 잘못되거나, 예상치 못한 동작이 발생할 수 있음
### 최적화
* 스트림 병렬화는 오직 성능 최적화의 수단일 뿐
  * 다른 최적화와 마찬가지로 변경 전후로 반드시 성능을 테스트하여 병렬화를 사용할 가치가 있는지 확인해야 함
* 그런 경우는 많지 않으나, 조건이 잘 갖춰지면 parallel 메서드 호출 하나로 프로세서 코어 수에 비례하는 성능 향상을 얻을 수 있음
#### 소수 계산 스트림 파이프라인 - 병렬화에 적합
```java
public class ProbablePrime {
    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }
}
```
#### 소수 계산 스트림 파이프라인 - 병렬화 버전
```java
public class ProbablePrime {
    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel() // 기존 로직에 추가된 부분
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }
}
```
---
[[Prev act >>]](../act6/README.md)  
[[Next act >>]](../../chapter8/act1/README.md)
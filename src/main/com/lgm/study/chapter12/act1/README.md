# 자바 직렬화의 대안을 찾으라
### 자바 직렬화
* 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 byte 형태로 데이터 변환하는 기술과 byte로 변환된 데이터를 다시 객체로 변환하는 기술(역직렬화)
  * 자바가 객체를 byte 스트림으로 인코딩 (직렬화)
  * byte 스트림을 객체로 만듬 (역직렬화)
### 직렬화 단점
* 근본적인 문제로 공격 범위가 너무 넓고 지속적으로 더 넓어져 방어하기 어려움
  * ObjectInputStream의 readObject 메서드를 호출하면 객체가 역직렬화되기 때문
### ObjectInputStream
* 객체 스트림
  * 객체 안에 저장되어 있는 내용을 파일로 저장하거나 네트워크를 통해 다른 곳으로 전송하려면 객체를 byte 형태로 분해해야 하는데, 이를 위한 객체 스트림
* 객체 전송 단계
    1. 직렬화된 객체를 byte 단위로 분해 (marshalling)
    2. 직렬화되어 분해된 데이터를 순서에 따라 전송
    3. 전송 받은 데이터를 원래대로 복구 (unmarshalling)
* ObjectInputStream 메서드  

|메서드명|설명|
|:---:|:---:|
|int available()|객체에서 읽을 수 있는 byte 값을 반환|
|void close()|객체 닫기|
|int read()|데이터를 byte 단위로 읽기|
|byte readByte()|객체의 1byte 읽기 (8 bit)|
|void readFully(byte[] buf, int off, int len)|객체에서 buf만큼 off 부터 len 만큼 읽기|
|Object readObject()|객체에서 Object 읽기|
  
### 가젯 (gadget)
* 역직렬화 과정에서 호출되는 잠재적으로 위험한 동작을 수행하는 메서드
### 역직렬화 폭탄 (deserialized bomb)
* 역직렬화 시간이 오래 걸리는 짧은 스트림을 역직렬화하여 서비스 거부 공격에 쉽게 노출될 때, 해당 스트림을 의미
* 예) 역직렬화 하는 순간 다운
  ```java
   static byte[] bomb() {
    Set<Object> root = new HashSet<>();
    Set<Object> s1 = root;
    Set<Object> s2 = new HashSet<>();
 
    for (int i=0; i<100; i++) {
        Set<Object> t1 = new HashSet<>();
        Set<Object> t2 = new HashSet<>();
        t1.add("foo");
        s1.add(t1);
        s1.add(t2);
        s2.add(t1);
        s2.add(t2);
        s1 = t1;
        s2 = t2;
    }
    return serialize(root);
  }
  ```
* `HashSet` 인스턴스를 역직렬화하려면 그 원소들의 해시코드를 계산해야 함
  * 대략 2^100 번 넘게 hashCode 메서드를 호출해야 함
### 대안
* 직렬화 위험을 회피하는 가장 좋은 방법은 **아무것도 역직렬화하지 않는 것**
* 새로 작성하는 시스템에서 자바 직렬화를 써야 하는 이유는 전혀 없음
* 객체와 바이트 시퀀스를 변환해주는 다른 매커니즘이 많음
  * 직렬화 시스템, 크로스-플랫폼 구조화된 데이터 표현
    * 예) Json, 프로토콜 버퍼 (Protocol Buffers; protobuf)
---
[[Prev act >>]](../../chapter11/act7/README.md)  
[[Next act >>]](../act2/README.md)
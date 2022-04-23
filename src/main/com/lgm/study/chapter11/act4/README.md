# wait와 notify보다는 동시성 유틸리티를 애용하라
### wait와 notify
* **스레드의 상태 제어**를 위한 메서드
* `wait()`는 가지고 있던 고유 락을 해제하고, 스레드를 잠들게 하는 역할을 하는 메서드
* `notify()`는 잠들어 있던 스레드 중 임의로 하나를 골라 깨우는 역할을 하는 메서드
* wait와 notify는 올바르게 사용하기 매우 까다로움
  * 대신 **고수준 동시성 유틸리티**를 사용하는 것이 좋음
### 고수준 동시성 유틸리티`
1. 실행자 프레임워크
2. 동시성 컬렉션 (concurrent collection)
    * 표준 컬렉션(`List`, `Queue`, `Map`)에 동시성이 더해진 것
      * 예) `CopyOnWriteArrayList`, `ConcurrentHashMap`, `ConcurrentLinkedQueue`
    * 동시성 컬렉션의 동시성을 무력화하는 건 불가능하며 외부에서 락을 걸면 오히려 속도가 느려짐
    * 동시성을 무력화할 수 없으므로 여러 메서드를 원자적으로 묶어 호출할 수도 없음
      * 이런 문제의 해결을 위해 여러 기본 동작들을 하나로 묶는 **상태 의존적 메서드**가 추가됨
3. 동기화 장치 (synchronizer)
    * 컬렉션 인터페이스 중 일부는 작업이 성공적으로 완료될 때까지 기다리도록 확장
    * 예) **BlockingQueue**
    ```java
      public interface BlockingQueue<E> extends Queue<E> {
       E take() throws InterruptedException;
      }
    ```
   * BlockingQueue의 `take()`는 큐의 원소를 꺼내는 역할을 수행하면서 큐가 비어있으면 새로운 원소가 추가될 때까지 기다림
   #### 동기화 장치 종류
    1. `CountDownLatch`, `Semaphore`
        * 가장 자주 쓰임 
    2. `CylicBarrier`, `Exchanger`
        * 상대적으로 덜 쓰임
    3. Phaser
### wait와 notify를 사용해야 하는 상황엔
* 어쩔 수 없이 wait와 notify를 사용해야 하는 상황이라면,
    1. 반드시 **동기화 영역 안에서** 사용할 것
    2. 항상 **반복문 안**에서 사용할 것
* 일반적으로 notify 보다는 `notifyAll()`을 사용하는 편이 안전함
### 결론
* wait와 notify는 올바르게 사용하기가 매우 까다로우므로 고수준 동시성 유틸리티를 사용하자.
---
[[Prev act >>]](../act3/README.md)  
[[Next act >>]](../act5/README.md)
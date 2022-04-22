# 스레드보다는 실행자, 태스크, 스트림을 이용하라
* `java.util.concurrent` 패키지는 실행자 프레임워크라는 인터페이스 기반의 유연한 태스크 실행 기능을 담고 있음
```java
// 작업 큐 생성
ExecutorService executor = Executors.newSingleThreadExecutor();

// 이 Executor에 실행할 태스크를 넘김
executor.execute(runnable);

// Executor 종료시키기
executor.shutdown();
```
### 실행자 서비스의 주요 기능
* 특정 태스크가 완료되기를 기다림
* 태스크 모음 중 아무 태스크 하나(invokeAny 메서드) 또는 모든 태스크(invokeAll 메서드)가 완료되기를 기다림
* 실행자 서비스가 종료하기를 기다림 (awaitTermination 메서드)
* 완료된 태스크들의 결과를 차례로 받음 (ExecutorCompletionService 이용)
* 태스크를 특정 시간에 혹은 주기적으로 실행하게 함 (ScheduledThread PoolExecutor 이용)
### 그 외 기능
* 큐를 둘 이상의 스레드가 처리하게 하고자 한다면 간단히 다른 정적 팩터리를 이용하여 다른 종류의 실행자 서비스 (스레드 풀)을 생성함
* 작은 프로그램이나 가벼운 서버라면 Executors.newCachedThreadPool을 사용할 것
* 무거운 프로덕션 서버에서는 스레드 개수를 고전한 Executors.newFixedThreadPool을 선택하거나 완전히 통제할 수 있는 ThreadPoolExecutor를 직접 사용하는 편이 나음
### 주의해서 사용
* 작업 큐를 손수 만드는 일은 삼가고, 스레드를 직접 다루는 일도 일반적으로는 삼가야 함
  * 스레드를 직접 다루면 스레드가 작업 단위와 실행 메커니즘 역할을 모두 수행하게 됨
  * 반면, 프레임워크에서는 작업 단위와 실행 메커니즘이 분리되어 있어 의미가 명확함
* Runnable
* Callable
### 포크-조인 태스크 (ForkJoinTask)
* 자바 7이 되면서 지원
* ForkJoinTask의 인스턴스는 작은 하위 태스크로 나뉠 수 있고, ForkJoinPool을 구성하는 스레드들이 해당 태스크들을 처리하며, 일을 먼저 끝낸 스레드는 다른 스레드의 남은 태스크를 가져와 대신 처리할 수도 있음
* ForkJoinPool을 이용해 만든 병렬 스트림을 이용하면 적은 노력으로도 이점을 얻을 수 있음
---
[[Prev act >>]](../act2/README.md)  
[[Next act >>]](../act4/README.md)
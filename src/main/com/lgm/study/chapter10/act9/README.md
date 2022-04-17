# 예외를 무시하지 말라
### 예외 무시의 위험성
* 메서드 선언에 예외를 명시하는 이유는 해당 메서드를 사용할 때 적절한 조취를 취해달라고 말하는 것
* 예외는 문제 상황에 대처하기 위해 존재하는데, `catch` 블록을 비워두면 예외가 존재할 이유가 없어짐
### 예외를 무시해야 할 때
* 대표적인 예) `FileInputStream`을 닫을 때
  * (입력 전용 스트림이므로) 파일의 상태를 변경하지 않았으니 복구할 것이 없음
  * (스트림을 닫는다는 것은) 필요한 정보는 이미 다 읽었다는 뜻으로 남은 작업을 중단할 이유도 없음
* 예외를 무시하기로 했다면 `catch` 블록 안에 그렇게 결정한 이유를 주석으로 남기고 예외 변수 이름도 `ignore`로 바꿔놓도록 함
```java
Future<Integer> f = exec.submit(planarMap::chromaticNumber);
int numColors = 4; 
try {
	numColors = f.get(1L, TimeUnit.SECONDS);
} catch (TimeoutException | ExecutionException ignored) {
}
```
### 결론
* 예측할 수 있는 예외 상황이든 프로그래밍 오류든, 빈 `catch` 블록을 못 본 척 지나가면 프로그램은 오류를 내재한 채 동작하게 된다.
* 에외를 적절히 처리하면 오류를 피할 수 있다.
  * 무시하지 않고 바깥으로 전파되게만 해도 최소한 디버깅 정보를 남긴 채 프로그램이 신호 시 중단되게 할 수 있다.
---
[[Prev act >>]](../act7/README.md)  
[[Next act >>]](../../chapter11/act1/README.md)
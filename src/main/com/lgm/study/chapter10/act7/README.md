# 예외의 상세 메시지에 실패 관련 정보를 담으라
### 상태 메시지의 중요성
* 예외 정보가 프로그램 실패에 대해 얻을 수 있는 유일한 정보인 경우가 많음
* 프로그램이 실패한 경우를 재현하기가 힘들 때 더욱 자세한 정보를 얻기 어려움
### 실패 메시지
* 예외에 관련된 모든 매개변수와 필드값을 담을 것
* 예외 메시지는 장황해서는 안 되지만, 가독성보다는 담긴 내용이 더 중요
  * 예외 메시지를 읽는 사람이 사용자가 아닌 프로그램을 분석할 프로그래머이기 때문
* 예외 생성자를 통해 예외와 관련된 정보를 받아 상세 메시지를 미리 생성하는 것도 좋은 방법
```java
public IndexOutOfBoundsException(int lowerBound, int upperBound, int index) {
	super(String.format("최솟값: %d, 최댓값: %d, 인덱스: %d", lowerBound, upperBound, index));
	
	// 실패 정보 저장
    this.lowerBound = lowerBound;
	this.upperBound = upperBound;
	this.index = index;
}
```
---
[[Prev act >>]](../act6/README.md)  
[[Next act >>]](../act8/README.md)
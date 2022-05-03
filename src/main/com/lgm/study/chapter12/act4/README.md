# readObject 메서드는 방어적으로 작성하라
### readObject 메서드
* InputObjectStream / OutputObjectStream 클래스에 포함된 메서드가 readObject / writeObject
* 클래스에 readObject / writeObject 가 정의되어 있다면, 기본 직렬화 과정에서 해당 메서드를 통해 직렬화와 역직렬화를 수행함
### readObject 문제점
* 새로운 객체를 만들어내는 public 생성자와 같다고 할 수 있음
  * 생성자처럼 유효성검사, 벙어적 복사를 수행해야 함
  * 그렇지 않을 경우, 불변식을 보장하지 못함
### readObject 사용 팁
* readObject 메서드에서 재정의 가능 메서드를 호출해선 안 됨
  * 클래스가 `final`이 아닌 경우에만 해당
  * 이 클래스의 하위 클래스가 불리기 이전에 생성자의 재정의된 메서드가 실행되므로 오류를 뱉어낼 것
---
[[Prev act >>]](../act3/README.md)  
[[Next act >>]](../act5/README.md)
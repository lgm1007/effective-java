# `try-finally` 대신 `try-with-resources`를 사용하라
### `try-finally`
* 더 이상 자원을 회수하는 최선의 방책이 아니다
* 자원이 둘 이상이면 `try-finally` 방식은 너무 지저분하다
### `try-with-resources`
* 복수의 자원을 처리할 때 `try-with-resources`가 간결함
### 꼭 회수해야 하는 자원을 다룰 땐 `try-with-resources`를 사용하는 것을 권장, 코드가 더 짧고 분명해지며 만들어지는 예외 정보도 유용함  

---
[[Prev act >>]](../act8/README.md)  
[[Next act >>]](../../chapter3/act1/README.md)
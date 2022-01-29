# equals는 일반규약을 지켜 재정의하라
### 꼭 필요한 경우가 아니라면 equals를 재정의하지 않고 사용 권장
### equals를 재정의해야 하는 순간
* 두 객체에 대한 논리적 동치성 확인 시, 상위 클래스의 equals가 적합하게 재정의되지 않았을 때
### equals 재정의 시 지켜야 할 일반규약
* 반사성(reflexivity) : null이 아닌 모든 참조값 `x`에 대해, `x.equals(x)`는 true = 자기자신은 반드시 true
* 대칭성(symmetry) : null이 아닌 모든 참조값 `x`,`y`에 대해, `x.equals(y)`가 true 이면 `y.equals(x)`는 true 이다.
* 추이성(transitivity) : null이 아닌 모든 참조값 `x`,`y`,`z`에 대해, `x.equals(y)`가 true이고 `y.equals(z)`가 true 이면, `x.equals(z)`는 true 이다.
* 일관성(consistency) : null이 아닌 모든 참조값 `x`,`y`에 대해 `x.equals(y)`를 여러 번 반복해서 호출해도 항상 동일한 결과가 나와야 한다.
* Not Null : null이 아닌 모든 참조값 `x`에 대해 `x.equals(null)`은 false 이다. `NullPointException`을 발생시키는 코드조차 허용 안 함
---
[[Prev act >>]](../../chapter2/act9/README.md)  
[[Next act >>]](../act2/README.md)
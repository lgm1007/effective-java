# public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
### public 클래스에 public 필드
* 불변성을 보장할 수 없고, 외부에서 접근할 때 수정 등 부수적인 작업을 할 수도 있음
* 필드의 접근 권한을 private로 변경하고 접근자 메서드를 사용하는 방법으로 해결 가능
```java
class Point {
    private double x;   // private 필드 변경
    private double y;   // private 필드 변경
    
    // Getter, Setter 같은 접근자 메서드 사용
    public double getX() {return x;}
    public double getY() {return y;}
    
    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}
}
```
### 접근자 메서드의 이점
* 내부 표현방식을 언제든 변경 가능 (= 유연성 제공)

#### package-private class 또는 private 중첩 class 라면 public 필드로 노출해도 문제 없음

---
[[Prev act >>]](../act1/README.md)  
[[Next act >>]](../act3/README.md)
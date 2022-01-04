# 상속보다는 컴포지션을 사용하라
### 메서드 호출과 달리 상속은 캡슐화를 깨뜨린다
* 상위 클래스가 어떻게 구현되느냐에 따라 하위 클래스의 동작에 이상이 생길 수 있음
  * 상위 클래스는 릴리즈마다 내부 구현이 달라질 수 있음
  * 위 결과로 하위 클래스가 오작동할 수 있음
### 컴포지션을 사용하라
* 기존 클래스를 확장하는 대신 새로운 클래스를 만들고 private 필드로 기존 클래스의 인스턴스 참조
* 컴포지션으로 새 클래스의 인스턴스 메서드들은 기존 클래스에 대응하는 메서드를 호출해 그 결과를 반환
```java
public interface Connections {
    public Connection makeConnection();
    ...
}

public class OConnections implements Connections {
    public Connection makeConnection() {
        // Codes ... 
        // return Connection Object
    }
}

public class ObjDto {
    // 컴포지션 사용
    private Connections connections;
    
    public ObjDto(Connections connections) {
        this.connections = connections;
    }
    
    public add(AObj aObj) {
        // 컴포지션 메서드 호출
        Connection c = connections.makeConnection();
        ...
    }
}

// In Use
Connections connections = new OConnections();
ObjDto objDto = new ObjDto(connections);
```
### 상속을 사용하기 전 점검 사항
1. 확장하려는 클래스의 API에 아무런 결함이 없는가?
2. 결함이 있다면, 이 결함이 새로운 클래스의 API까지 전파되어도 괜찮은가?
   * 컴포지션 : 결함을 숨기는 새로운 API 설계 가능
   * 상속 : 상위 클래스의 API를 결함까지 그대로 승계

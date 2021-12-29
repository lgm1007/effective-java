# 클래스와 멤버의 접근 권한을 최소화하라
### 정보은닉(캡슐화)
* 잘 설계된 컴포넌트는 내부 데이터를 외부에 얼마나 잘 숨겼는가로 알 수 있음
* 잘 설계된 컴포넌트는 모든 내부 구현을 숨겨 구현과 API를 깔끔하게 분리함
* 오직 API를 통해서만 다른 컴포넌트와 소통
* 장점
1. 여러 컴포넌트를 병렬로 개발하여 시스템 개발 속도를 높임
2. 각 컴포넌트를 빠르게 파악하여 디버깅 가능, 다른 컴포넌트로 교체 부담이 적음
3. 정보 은닉이 성능을 높이진 않지만 성능의 최적화에 도움
4. 재사용성을 높임, 외부에 의존 안하고 독자적으로 동작 가능한 컴포넌트인 경우 여러 환경에서 쓰일 수 있음
5. 시스템 전체가 개발되지 않은 상태에서 개별 컴포넌트 동작 검증 가능
### 모든 클래스와 멤버의 접근성을 가능한 좁혀라
* 클래스와 멤버의 접근제한자를 private 등으로 낮은 수준으로 부여
* 패키지 외부에서 클래스를 사용할 일이 없다면, package-private = default 로 선언하여 API가 아닌 내부에서 수정 가능하도록 함
### 한 클래스에서만 사용하는 클래스나 인터페이스는 private static 으로 중첩하라
* public일 필요 없는 클래스의 접근 수준을 낮출 것
### 멤버에 부여할 수 있는 접근제한자
* private : 해당 멤버를 선언한 클래스에서만 접근 가능
* package-private(default) : 해당 멤버가 소속된 패키지 안의 모든 클래스에서 접근 가능, 접근제한자를 명시하지 않았을 경우 default 값 (단, 인터페이스의 default는 public)
* protected : default 범위 + 해당 멤버를 선언한 클래스를 상속받은 하위 클래스에서도 접근 가능
* public : 모든 곳에서 접근 가능
### public 클래스의 인스턴스 필드는 되도록 public이 아니어야 한다
* 필드가 가변 객체를 참조하거나, final이 아닌 인스턴스 필드를 public으로 선언하면 해당 필드에 값을 제한할 수 없어 불변성을 보장할 수 없게 됨
* 필드가 final이면서 불변 객체를 참조해도 문제가 될 수 있음: 이미 참조하고 있는 객체 자체가 변경될 경우
    * 예) 배열 : 배열을 참조하는 참조값은 같지만, 인덱스 값이 변경될 수 있기 때문
    * 해결 방법
    * 1) private 배열을 만들고, public 불변 리스트 추가하기
      ```java
      private static final Thing[] PRIVATE_VALUES = {...};
      public static final List<Thing> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
      ```
    * 2) private 배열을 만들고 그 복사본을 반환하는 메서드 추가하기
      ```java
      private static final Thing[] PRIVATE_VALUES = {...};
      public static final Thing[] values() {
          return PRIVATE_VALUES.clone();
      }
      ```
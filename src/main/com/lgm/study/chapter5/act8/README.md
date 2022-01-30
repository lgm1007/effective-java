# 타입 안전 이종 컨테이너를 고려하라
### 컨테이너란?
* 객체의 "저장" 관점에서 가장 유명한 방법 중 하나는 배열(array), 특히 primitive 타입 값들을 저장할 때 배열을 많이 사용함
* 하지만 크기가 한번 정해지면 바꿀 수 없다. 라는 배열의 큰 단점이 있어, 이로 인한 제약은 상당히 큼
* 위 문제의 해결 방안으로 `java.util` 라이브러리에는 컨테이너(container) 클래스들이 있음
* 기본 타입들 : `List`, `Set`, `Queue`, `Map`
### 타입 안전 이종 컨테이너 패턴 (type safe heterogeneous container pattern)
* 컨테이너 대신 키를 매개변수화한 다음, 컨테이너에 값을 추가/제거할 때 매개변수화한 키를 함께 제공
* 제네릭 타입 시스템이 값의 타입과 키가 같음을 보장해줄 것
* 위와 같은 설계 방식을 "타입 안전 이종 컨테이너 패턴"이라고 함
* `Set<E>`, `Map<K, V>` 처럼 클래스 레벨에서 매개변수화할 수 있는 타입 수는 제한적
* 타입의 수에 제약없이 유연하게 필요한 경우, 특정 타입 외에 다양한 타입을 지원해야 하는 경우가 있을 때 사용하는 패턴
* 예제 코드

```java
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }
    
    public <T> getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }
    
    public static void main(String[] args) {
        Favorites f = new Favorites();
        
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Calss.class, Favorites.class);

        // 각 타입의 Class 객체를 매개변수화한 키 역할로 사용
        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);

        System.out.println("%s %x %s\n", favoriteString, favoriteInteger, favoriteClass.getName());
    }
}
```
* 타입별로 즐겨 찾는 인스턴스를 저장하고 검색할 수 있는 Favorites 클래스 코드
* 각 타입의 Class 객체를 매개변수화한 키 역할로 사용하면 되는데, 동작하는 이유는 class의 클래스가 제네릭이기 때문
* class 리터럴의 타입은 Class가 아닌 `Class<T>`
* Favorites 인스턴스는 타입 안전함 (String을 요청했는데 Integer를 반환하는 일은 없음)
* 모든 키의 타입이 제각각이라, 일반적인 맵과 달리 여러가지 타입의 원소를 담을 수 있음
* **Favorites는 타입 안전 이종 컨테이너**라 할 수 있음
### Favorites 클래스의 제약
1. 악의적인 클라이언트가 Class 객체를 raw 타입으로 넘길 경우 타입 안전성이 깨짐
    * 타입 불변식을 어기는 일이 없도록 하려면 putFavorite 메서드에서 인수로 주어진 instance 타입이 type으로 명시한 타입과 같은지 확인하고, 동적 형변환(아래 코드)을 사용
    ```java
    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), type.cast(instance));
    }
    ```
2. 실체화 불가 타입에는 Favorites 클래스를 사용할 수 없다
    * 즐겨찾는 String이나 String[]은 저장할 수 있어도 즐겨 찾는 `List<String>`은 저장할 수 없음
    * `List<String>`을 저장하려는 코드는 컴파일되지 않음, `List<String>`용 클래스를 얻을 수 없기 때문
    * 이 두 번째 제약을 슈퍼 타입 토큰으로 해결하려는 시도도 있었음
    * Spring Framework에서는 아예 `ParameterizedTypeReference` 클래스로 미리 구현해놓음
### 결론
* 컨테이너에서 타입의 수에 제약없이 유연함이 필요한 경우, 특정 타입 외 다양한 타입을 지원해야 하는 경우 타입 안전 이종 컨테이너 패턴을 사용
* 컨테이너 자체가 아닌 키를 매개변수로 바꾸면 타입 안전 이종 컨테이너 패턴을 만들 수 있음
* 타입 안전 이종 컨테이너는 Class를 키로 사용하며, 이런 식으로 쓰이는 Class 객체를 타입 토큰이라고 함
---
[[Prev act >>]](../act7/README.md)  
[[Next act >>]](../../chapter6/act1/README.md)
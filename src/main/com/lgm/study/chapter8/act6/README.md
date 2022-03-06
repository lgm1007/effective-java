# null이 아닌, 빈 컬렉션이나 배열을 반환하라
### 컬렉션이 비었으면 null을 반환 - 따라하지 말 것!
```java
private final List<Product> productsInStock = ...;

// 재고가 없는 경우 null 반환
public List<Product> getProducts() {
    return productsInStock.isEmpty() ? null : new ArrayList<>(productsInStock);
}
```
* 재고가 없다고 해서 특별히 취급할 이유는 없음
* 위 코드처럼 null을 반환할 경우, 클라이언트는 null을 처리하는 코드를 추가로 작성해야 함
  * 코드가 복잡해짐
```java
List<Product> products = shop.getProducts();
if(products != null && products.contains(Product.A)) {
    // ...
}
```
### 빈 컨테이너보다 null을 반환하는 것이 나은가?
* 이는 잘못된 주장
  * 빈 컬렉션과 배열은 굳이 새로 할당하지 않고 반환할 수 있음
* 가능성은 적지만, 사용 패턴에 따라 빈 컬렉션이 성능을 눈에 띄게 떨어뜨릴 수 있음
  * 불변 컬렉션을 반환하는 것
  * 불변 객체는 자유롭게 공유해도 안전하기 때문 (ex. `Collections.emptyList`, `Collections.emptySet`, `Collections.emptyMap`);
* 단, 이 역시 최적화에 해당하므로 꼭 필요할 때만 사용할 것
  * 최적화가 필요하다고 판단되면 수정 전과 후의 성능을 측정하여 실제로 성능이 개선되는지 확인해야 함
* 배열을 사용할 때도 마찬가지로, 절대 null을 반환하지 말고 길이가 0인 배열을 반환할 것
```java
private static final Product[] EMPTY_PRODUCT_ARRAY = new Product[0];

public Product[] getProducts() {
    return productsInStock.toArray(EMPTY_PRODUCT_ARRAY);
}
```
### 결론
* null이 아닌, 빈 배열이나 컬렉션을 반환하라.
  * null을 반환하는 API는 사용하기 어렵고 오류 처리 코드도 늘어난다.
  * 또한, 성능이 좋은 편도 아니다.
---
[[Prev act >>]](../act5/README.md)  
[[Next act >>]](../act7/README.md)
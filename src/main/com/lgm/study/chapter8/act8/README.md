# 공개된 API 요소에는 항상 문서화 주석을 작성하라
* 자바독은 소스코드 파일에서 문서화 주석이라는 특수한 형태로 기술된 설명을 추려 API 문서로 변환해줌
* 자바 버전에 따라 자바독 태그도 변화해옴
  * 자바 5 : `@literal`, `@code`
  * 자바 6 : `@implSpec`
  * 자바 9 : `@index`
* API를 올바로 문서화하기 위해선 **공개된 모든 클래스, 인터페이스, 메서드, 필드 선언에 문서화 주석을 달아야 함**
```java
/**
 * {@code AbstractQueuedLongSynchronizer}.
 *
 * <p>This class is Serializable, but all fields are transient,
 * so deserialized conditions have no waiters.
 *
 * @since 1.6
 */
public class ConditionObject implements Condition, java.io.Serializable {
```
* 기본 생성자에는 문서화 주석을 달 수 없으니, 공개 클래스는 절대 기본 생성자를 사용해서는 안 됨
* 메서드용 문서화 주석에는 해당 메서드와 클라이언트 사이의 규약을 명료하게 기술해야 함
  * `how`가 아닌 `what`을 기술해야 함
  * 전제조건(precondition)을 모두 나열해야 함
  * 메서드가 성공적으로 수행된 후 만족해야 하는 사후조건도 모두 나열해야 함
  * 전제조건은 `@throws` 태그로 비검사 예외를 선언하여 암시적으로 기술함
  * `@param` 태그를 이용하여 그 조건에 영향받는 매개변수에 기술해야 함
* 관례상 `@param` 태그와 `@return` 태그의 설명은 해당 매개변수가 뜻하는 값이나 반환값을 설명하는 명사구를 사용함
* 예) BigInteger 의 API 문서 참조
```java
/**
 * Returns a BigInteger whose value is {@code (this * val)}.
 *
 * @implNote An implementation may offer better algorithmic
 * performance when {@code val == this}.
 *
 * @param  val value to be multiplied by this BigInteger.
 * @return {@code this * val}
 */
public BigInteger multiply(BigInteger val) {
    return multiply(val, false);
}
```
* `@implSpec` 주석
  * 해당 메서드와 하위 클래스 사이의 관계를 설명하여, 하위 클래스들이 해당 메서드를 상속하거나 super 키워드를 통해 호출할 때 그 메서드가 어떻게 동작하는지를 명확히 인지하고 사용하게끔 해야 함
* 열거 타입을 문서화할 때는 상수들에도 주석을 달아야 함
```java
/**
 * An instrument section of a symphony orchestra.
 */
public enum OrchestraSection {
	/** Woodwinds, such as flute, clarinet, and oboe. */
	WOODWIND,

	/** Brass instruments, such as french horn and trumpet */
	BRASS,

	/** Percussion instruments, such as timpani and cymbals. */
	PERCUSSION
}
```
* 클래스 혹은 정적 메서드가 스레드 안전하든 그렇지 않든, 스레드 안전 수준을 반드시 API 설명에 포함해야 함
* 자바독은 메서드 주석을 상속시킬 수 있음
  * `{@InhertitDocks}` 태그를 사용해 상위 타입의 문서화 주석을 재사용할 수 있음
```java
public class MethodBuilder extends AbstractMemberBuilder {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasMembersToDocument() {
	    return !methods.isEmpty();
	}
}
```
### 결론
* 문서화 주석은 API를 문서화하는 가장 효과적인 방법이다.
* 공개 API라면 빠짐없이 문서화 주석을 달아야 한다.
---
[[Prev act >>]](../act7/README.md)  
[[Next act >>]](../../chapter9/act1/README.md)
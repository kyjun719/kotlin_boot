package com.jun.booklivecoding.kotlindocs.classesandobjects

/*
코틀린의 visibility modifier은 자바의 접근제한자로 보면됨
Classes, objects, interfaces, constructors, functions, properties 그리고 그들의 세터는 가시성 변경자를 가질 수 있음
(게터는 항상 프로퍼티와 같은 가시성을 가짐)
코틀린에는 private, protected, internal, public 4개의 가시성 수정자가 있음
기본 가시성은 public임

수정자가 다양한 유형의 선언 범위에 적용되는 방법을 알아봄
 */

/*
패키지
함수, 속성 및 클래스, 객체 및 인터페이스는 "최상위 수준", 즉 패키지 내에서 직접 선언 할 수 있음
- 가시성 변경자가 없으면 기본적으로 public으로 선언되며, 모든곳에서 선언한것을 볼 수 있음
- private로 선언하면, 해당 선언이 있는 파일 내에서만 볼 수 있음
- internal로 선언하면, 같은 모듈내에서만 볼 수 있음
- protected는 최상위 선언에 사용할 수 없음
 */
/*
// file name: example.kt
package foo

private fun foo() { ... } // visible inside example.kt

public var bar: Int = 5 // property is visible everywhere
    private set         // setter is visible only in example.kt

internal val baz = 6    // visible inside the same module
 */

/*
클래스와 인터페이스
클래스 안에 선언되는 멤버들의 가시성 변경자는 다음과 같음
- private는 클래스 내에서만 사용할 수 있음
- protected는 클래스 내부와 서브 클래스에서 사용할 수 있음
- internal은 모듈 내에 있는 해당 클래스와 해당 변수를 볼 수 있는곳에서 사용할 수 있음
- public은 이 public 멤버를 볼 수 있는 곳에서 사용할 수 있음

코틀린에서는, 내부 클래스의 private 변수는 외부클래스에서 볼 수 없음

만약 protected 멤버를 재정의하고 가시성을 수정하지 않았으면, 재정의한 멤버는 protected임
 */

open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4  // public by default

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a는 보이지 않음
    // b, c, d는 보임
    // Nested와 변수 e는 보임

    override val b = 5   // 'b' is protected
}

class Unrelated(o: Outer) {
    // o.a, o.b 는 보이지 않음
    // o.c, o.d는 같은 모듈이므로 보임
    // Outer.Nested가 보이지 않으므로, and Nested의 e 도 보이지 않음
}


/*
생성자
주 생성자의 가시성을 수정하기 위해서는, 다음과 같이 명시적으로 constructor 키워드를 사용해서 변경해야함
 */
class C private constructor(a: Int) {  }

/*
기본적으로, 모든 생성자는 public이며, 해당 가시성은 클래스를 접근 할 수 있는곳 모든곳에서 적용됨.
internal 클래스의 생성자는 같은 모듈 내에서만 접근 가능함
 */

/*
지역변수, 함수, 클래스는 가시성 변경자를 가지지 않음
 */

/*
internal 가시성 변경자는 같은 모듈 내에서만 볼 수 있음
모듈은 함께 컴파일 된 Kotlin 파일 집합임
- IntelliJ IDEA 모듈
- Maven 프로젝트
- Gradle 소스 셋 (테스트 소스 세트가 main의 내부 선언에 액세스 할 수 있다는 점을 제외하고)
- <kotlinc> Ant 태스크를 한 번 호출하여 컴파일 된 파일 세트.
 */

// https://kotlinlang.org/docs/reference/visibility-modifiers.html
package com.jun.booklivecoding.kotlindocs.classesandobjects

import javax.inject.Inject

/*
뮤터블(변경가능한) 변수는 var, 변경 불가능한 변수는 val을 사용함
 */
class Address {
    val name: String = "abc"
    var address : String = "zzz"
}
/*
변수는 다음과 같이 접근함
 */
fun test() {
    val a = Address();
    println("name:: ${a.name}")
    //can not update val properties
    //a.name = "sss"

    println("address:: ${a.address}")
    a.address = "ccc"
    println("address:: ${a.address}")
}

/*
getter과 setter은 다음과 같이 선언됨
var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]
초기자, 게터, 세터 설정은 선택이며 초기자가 있는경우 프로퍼티의 타입도 생략가능함
 */
//var의 경우 초기자가 필요함
//var a: Int?
//Int형 자료형이며, 기본 게터와 세터를 가짐
var b = 1

//읽기전용 변수를 선언하는 것은 다음과 같음
// 1) var대신 val로 선언함
// 2) setter를 허용하지 않음
// custom getter과 setter은 다음과 같이 선언함
// setter에서 파라미터는 관습적으로 value를 사용함
class CustomGetterAndSetter {
    var tmp: String
        get() = this.toString()
        set(value) {
            setDataFromString(value)
        }

    // 코들린 1.1부터 getter에서 변수타입을 추론할 수 있으면 타입선언을 생략할 수 있음
    val isEmpty get()=tmp.length==0

    private fun setDataFromString(value: String): String {
        return value
    }
}

// 접근자의 가시성이나 애노테이션을 변경하지만 구현은 변경하지 않는경우 접근자의 내용 선언 없이 접근자를 선언할 수 있음
class UseDefaultAccessor {
    // setter을 private로 변경하고 기본 구현을 사용함
    var setterVisibility: String = "abc"
        private set
    //setter에 Inject 애노테이션을 추가함
    var setterWithAnnotation: Any? = null
        @Inject set
}

/*
지원 필드(backing field)
코틀린에서 필드는 해당 값을 메모리에 유지하기 위해 속성의 일부로 필요할 때만 사용됨
필드는 직접 선언 할 수 없음
그러나 속성에 지원 필드가 필요한 경우 코틀린은 이를 자동으로 제공함. 이 지원 필드는 field 식별자를 사용하여 접근자에서 참조 할 수 있음
field 식별자는 프로퍼티의 접근자에서만 사용할 수 있음
하나 이상의 접근자의 기본 구현을 사용하거나 사용자 지정 접근자가 field 식별자를 통해 이를 참조하는 경우 속성에 대한 지원 필드가 생성됨.
 */
var counter = 0 //초기자에서 지원필드에 직접 접근함
    set(value) {
        if(value > 0) field=value
    }

/*
암시적 지원 필드(backing field) 스킴과 맞지 않는경우, 언제든지 지원 속성(backing property)를 가질 수 있음
 */
private var _table: Map<String, Int>? = null
public val table: Map<String, Int>
    get() {
        if (_table == null) {
            _table = HashMap() // Type parameters are inferred
        }
        return _table ?: throw AssertionError("Set to null by another thread")
    }
// jvm에서는 기본 getter 및 setter를 사용하여 개인 속성에 대한 액세스가 최적화되므로이 경우 함수 호출 오버 헤드가 발생하지 않습니다.

/*
컴파일 시간 상수(Compile-Time Constants)
어떤 변수가 컴파일 할 때 읽기전용 프로퍼티로 알려져 있으면, const 접근자를 사용하여 컴파일 시간 상수임을 선언함.
다음과 같은 조건들을 만족해야함
- object 또는 companion object의 최상위 또는 멤버
- 변수타입이 String 또는 원시타입으로 선언되어야 함
- 사용자정의 게터가 없어야함
 */
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"
@Deprecated(SUBSYSTEM_DEPRECATED) fun foo() {  }

/*
늦게 초기화되는(Late-Initialized) 프로퍼티와 변수
보통 null이 아닌 타입의 프로퍼티는 생성자에서 초기화됨
그러나 의존성주입이나 단위테스트의 설정 메소드에서 초기화 될 수 있음
이때 생성자에 null이 아닌 초기자를 넣을 수 없지만, 프로퍼티를 사용할 때 null 체크를 하고시지 않은경우, lateinit 접근자를 사용하여 해결할 수 있음
 */
/*
public class MyTest {
    lateinit var subject: TestSubject

    @SetUp fun setup() {
        subject = TestSubject()
    }

    @Test fun test() {
        subject.method()  // dereference directly
    }
}
 */

/*
lateinit을 쓰기 위해서는 클래스 내부에 주 생성자가 아닌 클래스 내부에 var로 선언되어야 하며, 사용자정의 게터와 세터가 없어야함
코틀린 1.2부터는 최상위 프로퍼티와 지역변수 이여야함.
프로퍼티나 변수는 null이 아니여야하며, 반드시 원시타입이 아니여야함

lateinit 변수를 초기화전에 접근하는 경우 exception이 발생함
 */

/*
1.2부터는 .isInitialized를를 통서 초기화 여부를 확인할 수 있음

if (foo::bar.isInitialized) {
    println(foo.bar)
}
 */


/*
위임된 속성(Delegated Properties)
가장 일반적인 종류의 속성은 단순히 지원 필드(backing field)에서 읽고 쓸 수 있음
반면에 사용자 정의 getter 및 setter를 사용하면 속성의 모든 동작을 구현할 수 있음
그 사이 어딘가에는 속성이 작동하는 방식에 대한 특정 공통 패턴이 있습니다.
몇 가지 예 : 지연 값(lazy values), 주어진 키로 맵에서 읽기, 데이터베이스 액세스, 액세스시 리스너에게 알림 등

이러한 일반적인 동작은 위임 된 속성을 사용하여 라이브러리로 구현할 수 있습니다.
 */
//https://kotlinlang.org/docs/reference/properties.html
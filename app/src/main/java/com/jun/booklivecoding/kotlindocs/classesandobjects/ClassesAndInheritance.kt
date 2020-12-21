package com.jun.booklivecoding.kotlindocs.classesandobjects

import java.util.*
import javax.inject.Inject

//코틀린에서 클래스는 다음과 같이 선언함
class Invoice { }

// 클래스 선언은 클래스 이름, 클래스 헤더(타입 파라미터나 주 생성자 등), 중괄호로 둘러쌓인 클래스 바디로 이루어짐
//해더와 바디는 선택적이며, 바디가 없는경우 중괄호는 생략가능
class Empty

// 생성자
// 코틀린의 클래스는 주 생성자와 하나이상의 부 생성자를 가질수 있음
// 주 생성자는 클래스 헤더에 포함됨
open class Person constructor(firstName: String) {  }

// 주 생성자가 어떤 애노테이션이나 접근 수정자를 가지고 있지 않으면 constructor 키워드는 생략될 수 있음
class Person2(firstName: String) {  }

// 주 생성자는 코드를 포함하지 못함. 초기화 블록은 init {}에 포함됨
// 인스턴스 초기화시 클래스 바디에 나타나는 순서대로 초기화 블록이 실행됨
// 변수도 init 블록과 함께 순서대로 실행됨
// 주 생성자의 파라미터는 init 블록에 사용될 수 있음.
class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)
    val secondProperty = "Second property: ${name.length}".also(::println)
    init {
        println("First initializer block that prints ${name}")
    }

    init {
        println("Second initializer block that prints ${name.length}")
    }
}
/*
다음과 같이 출력됨
First property: hello
Second property: 5
First initializer block that prints hello
Second initializer block that prints 5
 */

// 클래스 바디에 변수 초기화 부분을 넣을 수 있음
class Customer(name: String) {
    val key = name.toUpperCase(Locale.ROOT)
}

// 다음과 같이 주 생성자에 변수를 선언할 수 있음
class Person3(val firstName: String, val lastName: String, val age: Int) {

}

// 클래스 변수를 선언할 때 후행 쉼표를 사용할 수 있음
// 후행 쉼표는 일련의 요소 중 마지막 항목 뒤의 쉼표 기호입니다.
class Person4(
    val firstName: String,
    val lastName: String,
    val age: Int,   //trailing comma
) {  }

// 기본 생성자에 선언 된 속성은 변경 가능 (var) 또는 읽기 전용 (val)일 수 있음
// 만약 생성자에 접근 수정자나 애노테이션이 있는 경우 constructor 키워드가 필요하며 수정자가 이 앞에 옴
class Customer2 public @Inject constructor(name: String) { /*...*/ }


// constructor 키워드를 사용하여 부 생성자를 만들수 있음
class Person5 {
    var children: MutableList<Person5> = mutableListOf()
    constructor(parent: Person5) {
        parent.children.add(this)
    }
}

// 만약 주생성자가 있으면, 모든 부 생성자는 직/간접적으로 주 생성자에 위임해야함
// 동일 클래스내 생성자 위임은 this 키워드를 사용함
class Person6(val name: String) {
    var children: MutableList<Person6> = mutableListOf()
    //해당 부 생성자가 주 생성자로 위임함
    constructor(name: String, parent: Person6) : this(name) {
        parent.children.add(this)
    }
}

// 모든 초기화 블록과 변수 초기화는 보조 생성자보다 먼저 실행되므로 기본생성자의 역할도 할 수 있음
class Constructors {
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructor")
    }
}

// 비 추상 클래스가 어떠한 생성자도 선언하지 않으면 인자가 없는 주 생성자가 생성됨. 해당 생성자는 public 임
// 생성자를 public으로 가지지 않으려면 다른 접근제한자를 생성자에 추가함
class DontCreateMe private constructor () { /*...*/ }

// 참고 : JVM에서 기본 생성자의 모든 매개 변수에 기본값이 있는 경우 컴파일러는 기본값을 사용하는 추가 매개 변수없는 생성자를 생성함.
// 이렇게하면 매개 변수없는 생성자를 통해 클래스 인스턴스를 만드는 Jackson 또는 JPA와 같은 라이브러리와 함께 Kotlin을 더 쉽게 사용할 수 있음


// 클래스의 인스턴스를 만들기 위해서는 함수처럼 생성자를 호출할 수 있음. 코틀린은 new 키워드가 없음
fun test1() {
    val invoice = Invoice()
    val customer = Customer("Joe Smith")
}

/*
클래스는 다음과 같은 항목들을 포함할 수 있음
- 생성자와 init 블록
- 함수
- 프로퍼티
- 중첩 또는 내부 클래스
- object 선언
 */

// 상속
// 코틀린의 모든 클래스는 Any를 상속함
// Any는 equals(), hashCode(), toString()을 가지고 있음
// 기본적으로 코틀린의 클래스는 final이므로, 상속되지 않음
// 상속하기 위해서는 open 키워드를 사용해야함
// 명시적으로 상위 클래스를 지정하지 위해서는 클래스 해더 옆에 : 을 사용한 후 표시함
open class Base(p: Int)
class Derived(p: Int) : Base(p)

// 파생 클래스(Derived)에 주 생성자가 있으면, 기본 클래스(Base)는 자식 클래스에 선언된 주 생성자의 파라미터를 사용하여 생성자가 호출 되어야 함

// 파생클래스에 주 생성자가 없는 경우, 부생성자(secondary constructor)에서 super 키워드를 사용하여 초기화 시켜줘야함
// 또는 super 클래스를 사용하는 다른 생성자로 위임함
// 부생성자(secondary constructor)에서는 다른 타입의 생성자를 호출할 수 있음
/*
class MyView : View {
    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
}
*/

// 메소드 오버라이딩
// 해당 멤버가 오버라이딩 가능함을 알리기 위해서는 open 키워드를 사용해야함
open class Shape {
    open fun draw() { /*...*/ }
    fun fill() { /*...*/ }
}

class Circle() : Shape() {
    override fun draw() { /*...*/ }
}

// Circle에서 draw에 override를 붙이지 않는경우 컴파일러가 에러를 보여줌
// fill() 메소드를 오버라이드 하거나 똑같은 이름의 메소드를 사용하려는 경우 컴파일러가 에러를 보여줌
// 클래스에 open 키워드가 없는경우 멤버에 open을 추가하여도 효과가 없음
// 재 오버라이딩을 막기 위해서는 final 키워드를 사용함
open class Rectangle() : Shape() {
    final override fun draw() { /*...*/ }
}


// 프로퍼티 오버라이딩
// 프로퍼티도 메소드와 마찬가지로 override를 사용하여 상속할 수 있음
/*
open class Shape {
    open val vertexCount: Int = 0
}

class Rectangle : Shape() {
    override val vertexCount = 4
}

 */

// val로 선언된 변수를 var로 상속받은 후 변경할 수 있지만 반대는 안됨
// val을 var로 변경한다는것은 set 메소드를 추가하는것이지만, 그 반대로 set메소드를 삭제하는것은 안됨
// 주 생성자에 override 키워드를 넣어서 사용할 수 있음
/*
interface Shape {
    val vertexCount: Int
}

class Rectangle(override val vertexCount: Int = 4) : Shape // Always has 4 vertices

class Polygon : Shape {
    override var vertexCount: Int = 0  // Can be set to any number later
}

 */

// 파생 클래스의 초기화 순서
// 기본 클래스의 초기화가 먼저 된 다음 파생 클래스의 초기화가 진행됨
/*
open class Base(val name: String) {

    init { println("Initializing Base") }

    open val size: Int =
        name.length.also { println("Initializing size in Base: $it") }
}

class Derived(
    name: String,
    val lastName: String,
) : Base(name.capitalize().also { println("Argument for Base: $it") }) {

    init { println("Initializing Derived") }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

fun main() {
    println("Constructing Derived(\"hello\", \"world\")")
    val d = Derived("hello", "world")
}

// 출력결과
Argument for Base: Hello
Initializing Base
Initializing size in Base: 5
Initializing Derived
Initializing size in Derived: 10
 */

/*
기본 클래스 생성자가 실행될 때 파생 클래스에서 선언되거나 오버라이드된 속성은 아직 초기화되지 않음
이러한 프로퍼티 중 하나가 기본 클래스 초기화에서 사용되는 경우 (직접적으로 또는 다른 재정의된 open 멤버 구현을 통해)
잘못된 동작이나 런타임 오류가 발생할 수 있음
따라서 기본 클래스를 디자인 할 때 생성자, 프로퍼티 이니셜라이저 및 init 블록에서 open 멤버를 사용하지 않아야함
 */

// 상위 클래스의 구현 호출
// 파생 클래스에서 super 키워드를 사용하여 기본 클래스의 프로퍼티나 함수에 접근할 수 있음
/*
open class Rectangle {
    open fun draw() { println("Drawing a rectangle") }
    val borderColor: String get() = "black"
}

class FilledRectangle : Rectangle() {
    override fun draw() {
        super.draw()
        println("Filling the rectangle")
    }

    val fillColor: String get() = super.borderColor
}
 */

// 내부클래스에서는 super 키워드와 함께 바깥 클래스의 애노테이션과 함께 사용함
/*
class FilledRectangle: Rectangle() {
    fun draw() { /* ... */ }
    val borderColor: String get() = "black"

    inner class Filler {
        fun fill() { /* ... */ }
        fun drawAndFill() {
            super@FilledRectangle.draw() // Calls Rectangle's implementation of draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}") // Uses Rectangle's implementation of borderColor's get()
        }
    }
}

 */

// 오버라이딩 규칙
/*
어느 클래스의 직계 수퍼 클래스에서 동일한 멤버들을 가지고 있는 경우, 이 멤버를 재정의하고 상속된 항목 중 하나를 사용하여 구현해야함
수퍼 클래스에 구현된 프로퍼티 또는 함수에 접근하려는 경우 다음과 같이 수퍼클래스의 이름을 명시해야함 super <Base>
 */

/*
open class Rectangle {
    open fun draw() { /* ... */ }
}

interface Polygon {
    fun draw() { /* ... */ } // interface members are 'open' by default
}

class Square() : Rectangle(), Polygon {
    // The compiler requires draw() to be overridden:
    override fun draw() {
        super<Rectangle>.draw() // call to Rectangle.draw()
        super<Polygon>.draw() // call to Polygon.draw()
    }
}
 */


// 추상 클래스
// 추상 멤버느 그 클래스 안에 구현되지 않음
// 상속클래스 애노테이션이나 open 키워드가 필요 없음
/*
open class Polygon {
    open fun draw() {}
}

abstract class Rectangle : Polygon() {
    abstract override fun draw()
}
 */

// companion object(동반 객체)
/*
클래스 인스턴스없이 호출 할 수 있지만 클래스의 내부(예 : 팩토리 메서드)에 액세스해야하는 함수를 작성해야하는 경우
해당 클래스 내에서 개체 선언의 멤버로 작성할 수 있음

더 구체적으로 말하자면, 클래스 내에서 동반 객체를 선언하면 클래스 이름만 한정자로 사용하여 해당 멤버에 액세스 할 수 있음
// 뒤에 Objects에서 설명함
 */


//https://kotlinlang.org/docs/reference/classes.html#classes-and-inheritance
fun main() {
    InitOrderDemo("hello")
}
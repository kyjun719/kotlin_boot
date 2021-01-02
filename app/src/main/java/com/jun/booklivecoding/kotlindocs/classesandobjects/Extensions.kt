package com.jun.booklivecoding.kotlindocs.classesandobjects

/*
코틀린은 클래스 상속이나 데코레이터 같은 디자인패턴의 사용 없이 클래스의 기능을 확장할 수 있음. 해당 기능은 확장이라고 부름
예를들어, 수정할 수 없는 서드파티 라이브러리의 클래스에 새로운 함수를 작성할 수 있음.
해당 함수들은 원래 클래스의 메소드를 사용하는것 처럼 호출하면됨. 이러한 작용을 확장함수라고 부름. 또한 확장속성도 존재함
 */

/*
확장함수
확장함수를 선언하기 전에, 확장되는 유형으로 이름을 접두사로 지정해야함
다음 예제는 MutableList<Int>에 swap을 추가함.
 */
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}
/*
위의 swap은 MutableList<Int>에서만 사용할 수 있지만, 제네릭 변수를 사용하여 모든 변수의 MutableList에 대해 사용할 수도 있음
 */
fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}

/*
확장 함수안의 this키워드는 응답객체(. 이전의 객체)를 나타냄. 함수는 다음과 같이 호출할 수 있음
 */
class ex1 {
    fun f1(){
        val list = mutableListOf(1, 2, 3)
        list.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'list'
        val list2 = mutableListOf("a","b","c")
        list2.swap2(0,2)
    }
}

/*
확장은 실제로 확장하는 클래스를 수정하지 않음.
확장을 정의하면 새 멤버를 클래스에 삽입하지 않고 이 유형의 변수에 점 표기법을 사용하여 새 함수를 호출 할 수 있게 만듦.

확장 함수는 정적으로 전달된다는 점을 강조하고 싶음. 즉, 수신자 유형에 따라 가상이 아님.
이는 호출되는 확장 함수가 런타임에 해당 표현식을 평가 한 결과의 유형이 아니라 함수가 호출된 표현식의 유형에 의해 결정됨을 의미함
 */

/*
아래 예제는 s가 Shape로 선언되어 있으므로, shape의 getName 함수가 호출됨
 */
class ex2 {
    open class Shape

    class Rectangle: Shape()

    fun Shape.getName() = "Shape"

    fun Rectangle.getName() = "Rectangle"

    fun printClassName(s: Shape) {
        println(s.getName())
    }

    fun main() {
        //파라미터로는 Rectangle을 넣었지만, 함수는 Shape의 확장함수가 호출됨
        printClassName(Rectangle())
    }
}

/*
만약 클래스에 멤버함수가 있고, 같은 리시버 타입, 같은 이름으로 확장함수가 정의되어 있으면 항상 멤버함수가 호출됨
 */
class ex3{
    class Example {
        fun printFunctionType() { println("Class method") }
    }

    fun Example.printFunctionType() { println("Extension function") }
    fun Example.printFunctionType(i: Int) { println("Extension function") }

    fun main() {
        //"Class method" 출력
        Example().printFunctionType()
        //"Extension function" 출력
        Example().printFunctionType(1)
    }
}


/*
확장은 null 가능한 리시버 타입으로 선언할 수 있음.
이런 확장함수는 객체값이 null 일때도 호출될 수 있으며, 내부에 null인지 확인할 수도 있음
 */
fun Any?.toString(): String {
    if (this == null) return "null"
    // null체크 이후에 this는 null이 아닌 타입으로 자동으로 캐스트 되므로, 모든 클래스의 toString()의 멤버 함수로 사용됨
    return toString()
}


/*
확장 프로퍼티
함수와 비슷하게, 확장 프로퍼티를 지원함
 */
val <T> List<T>.lastIndex: Int
    get() = size - 1

/*
확장은 실제로 멤버를 클래스에 삽입하지 않기 때문에 확장 프로퍼티에 지원 필드가 있는 효율적인 방법이 없음
이것은 확장 프로퍼티에 초기자를 사용하지 못하는 이유가 됨
이들의 동작은 게터와 세터를 명시적으로 제공해야만 정의할 수 있음

//확장 프로퍼티에 초기자는 허용되지 않음
val House.number = 1
 */

/*
컴퍼니언 객체(companion object) 확장
클래스에 컴퍼니언 객체가 정의되어 있으면, 컴퍼니언 객체에 확장함수와 확장프로퍼티를 정의할 수 있음
컴퍼니언 객체의 일반 멤버처럼 클래스이름을 한정자로 사용하여 호출할 수 있음
 */
class ex4{
    class MyClass {
        companion object { }  // will be called "Companion"
    }

    fun MyClass.Companion.printCompanion() { println("companion") }

    fun main() {
        MyClass.printCompanion()
    }
}

/*
확장의 범위
대부분의 확장은 패키지 바로아래에 선언됨

package org.example.declarations

fun List<String>.getLongestString() { /*...*/}
 */

/*
확장을 선언한 패키지 밖에서 사용하지 위해서는, import를 사용해야함

package org.example.usage

import org.example.declarations.getLongestString

fun main() {
    val list = listOf("red", "green", "blue")
    list.getLongestString()
}
 */


/*
멤버로써의 확장의 선언
클래스 내부에서, 다른클래스의 확장을 정의할 수 있음.
이러한 확장 내부에는 한정자 없이 접근할 수 있는 개체 멤버의 암시적 수신자가 여러개 있음
확장이 선언된 클래스의 인스턴스를 디스패치 수신자라고 하며, 확장 메소드의 수신자 유형 인스턴스를 확장 수신자라고 함

디스패치 수신자와 확장 수신자간에 이름이 충돌하는 경우 확장 수신자가 우선함
디스패치 수신자의 멤버를 참조하려면 정규화된 this 구문을 사용할 수 있음
 */

class ex5{
    class Host(val hostname: String) {
        fun printHostname() { print(hostname) }
    }

    class Connection(val host: Host, val port: Int) {
        fun printPort() { print(port) }

        fun Host.printConnectionString() {
            printHostname()   // calls Host.printHostname(), 확장 수신자의 메소드 호출
            print(":")
            printPort()   // calls Connection.printPort(), 디스패치 수신자의 메소드 호출
        }

        fun Host.getConnectionString2() {
            toString()         // calls Host.toString()
            this@Connection.toString()  // calls Connection.toString()
        }

        fun connect() {
            /*...*/
            host.printConnectionString()   // calls the extension function
        }
    }

    fun main() {
        Connection(Host("kotl.in"), 443).connect()
        //Host("kotl.in").printConnectionString(443)  // printConnectionString은 확장함수로 Connection에 선언되어 있으므로 에러 발생함
    }
}

/*
멤버로 선언된 확장은 open으로 선언되고 하위클래스에서 재정의 될 수 있음
이는 이러한 함수의 디스패치가 디스패치 수신자 유형에 대해서는 가상이지만 확장 수신자 유형에 대해서는 정적임
 */
class ex6{
    open class Base { }

    class Derived : Base() { }

    open class BaseCaller {
        open fun Base.printFunctionInfo() {
            println("Base extension function in BaseCaller")
        }

        open fun Derived.printFunctionInfo() {
            println("Derived extension function in BaseCaller")
        }

        fun call(b: Base) {
            b.printFunctionInfo()   // call the extension function
        }
    }

    class DerivedCaller: BaseCaller() {
        override fun Base.printFunctionInfo() {
            println("Base extension function in DerivedCaller")
        }

        override fun Derived.printFunctionInfo() {
            println("Derived extension function in DerivedCaller")
        }
    }

    fun main() {
        BaseCaller().call(Base())   // "Base extension function in BaseCaller"
        BaseCaller().call(Derived())   // "Base extension function in BaseCaller" - BaseCaller의 Base.printFunctionInfo() 함수 호출됨
        //BaseCaller에서 선언된 call이 호출되지만, printFunctionInfo은 DerivedCaller에서 재정의한 함수가 호출됨
        DerivedCaller().call(Base())  // "Base extension function in DerivedCaller" - dispatch receiver is resolved virtually
        //정적으로 정의된 Base의 printFunctionInfo로 호출됨
        DerivedCaller().call(Derived())  // "Base extension function in DerivedCaller" - extension receiver is resolved statically
    }
}

/*
확장은 동일한 범위에서 선언된 일반 함수와 동일한 다른 엔티티의 가시성으로 배포됨
- 파일의 최상위 수준에서 선언된 확장은 동일한 파일의 다른 private 최상위 수준 선언에 액세스 할 수 있음
- 확장이 수신자 유형 외부에서 선언되면 이러한 확장은 수신자의 private 멤버에 액세스 할 수 없음
 */

fun main() {
    ex6().main()
}


// https://kotlinlang.org/docs/reference/extensions.html
// https://medium.com/til-kotlin-ko/kotlin%EC%9D%98-extension%EC%9D%80-%EC%96%B4%EB%96%BB%EA%B2%8C-%EB%8F%99%EC%9E%91%ED%95%98%EB%8A%94%EA%B0%80-part-3-587cc37e7337
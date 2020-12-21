package com.jun.booklivecoding.kotlindocs.classesandobjects

/*
코틀린에서의 인터페이스는 추상메소드의 선언과 메소드 구현을 할 수 있음
클래스 상속과의 다른점은 인터페이스에서는 상태를 저장하지 못함
속성값을 가질수 있지만 추상화 되어있거나 접근자 구현을 제공해야함
 */
interface MyInterface {
    fun bar()
    fun foo() {
        // optional body
    }
}

/*
클래스나 object에서 다음과 같이 하나이상의 인터페이스를 구현할 수 있음
 */
class Child : MyInterface {
    override fun bar() {
        // body
    }
}

/*
프로퍼티는 인터페이스안에 선언할 수 있음. 인터페이스에 선언된 프로퍼티는 추상적이거나 접근자를 위한 구현을 제공할 수 있음.
인터페이스의 프로퍼티는 지원필드(backing field)가 없고, 접근자에서도 사용할 수 없음
 */
class tmp{
interface MyInterface {
    val prop: Int // abstract

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child : MyInterface {
    override val prop: Int = 29
}
}


/*
인터페이스는 다른 인터페이스에서 올 수도 있고, 멤버의 구현과 새로운 함수와 속성의 선언을 제공함
자연적으로, 이러한 인터페이스를 구현하는 클래스에서는 구현이 안된 멤버들만 구현하면됨
 */
class tmp2 {
interface Named {
    val name: String
}

interface Person : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

data class Employee(
    // implementing 'name' is not required
    override val firstName: String,
    override val lastName: String,
    val position: Int
) : Person
}

/*
수퍼타입 리스트에 많은 타입들을 선언할 때, 하나이상의 동일한 메소드를 구현할 경우가 있음
 */
class tmp3 {
interface A {
    fun foo() { print("A") }
    fun bar()
}

interface B {
    fun foo() { print("B") }
    fun bar() { print("bar") }
}

class C : A {
    override fun bar() { print("bar") }
}

class D : A, B {
    override fun foo() {
        //어느 인터페이스의 foo() 메소드를 사용할 지 언선해줘야함
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        //A에도 bar이 있지만, 구현된 메소드를 써야 하므로 자동으로 B의 bar()메소드를 사용함
        super<B>.bar()
    }
}
}


// https://kotlinlang.org/docs/reference/interfaces.html
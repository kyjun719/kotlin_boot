package com.jun.booklivecoding.kotlindocs.classesandobjects

class DataClasses {
    /*
    데이터 클래스의 주된 용도는 데이터를 가지고 있기 위해서 사용함
    이러한 클래스에서 일부 표준 기능 및 유틸리티 함수는 종종 데이터에서 기계적으로 파생 될 수 있음
    코틀린에서는 데이터 클래스라고 부르며 data 키워드를 사용함
     */
    data class User(val name: String, val age: Int)

    /*
    컴파일러는 기본 생성자에 선언된 모든 속성에서 다음 멤버를 자동으로 파생함
    - equals()/hashCode() 쌍
    - "User(name=John, age=42)" 형식의 toString()
    - 선언 순서에 따른 componentN() 함수에 대응하는 프로퍼티
    - copy() 함수

    생성된 코드의 일관성과 의미있는 동작을 보장하기 위해서는, 데이터 클래스는 다음 요구사항들을 충족해야함
    - 기본 생성자는 최소한 하나의 파라미터가 필요함
    - 모든 기본 생성자의 파라미터는 val 또는 var로 표기되어야 함
    - 데이터 클래스는 abstract, open, sealed, inner 을 사용할 수 없음
    - (1.1이전) 데이터 클래스는 인터페이스만 구현할 수 있음

    또한 멤버 생성은 멤버 상속과 관련하여 다음 규칙을 따름
    - 데이터 클래스 내부에 equals(), hashCode() 또는 toString()의 명시적 구현이 있거나 수퍼 클래스의 final 구현이 있는 경우
    이러한 함수는 생성되지 않고 기존 구현이 사용됨

    - 상위 유형에 open으로 선언되어 있고 호환 가능한 유형을 반환하는 componentN() 함수가 있는 경우
    해당 함수가 데이터 클래스에 대해 생성되고 상위 유형의 함수를 재정의함
    호환되지 않는 시그니처 또는 final로의 변환으로 인해 상위 유형의 기능을 대체 할 수 없는 경우 에러 발생함

    - 이미 일치하는 시그니처가 있는 copy (...)함수가 있는 유형에서 데이터 클래스를 파생하는 것은 Kotlin 1.2에서 더 이상 사용되지 않으며 Kotlin 1.3에서 금지됨

    - componentN() 및 copy() 함수에 대한 명시적 구현을 제공하는 것은 허용되지 않음

    1.1부터, 데이터 클래스는 다른클래스를 상속할 수 있음(sealed class 참조)
    JVM에서 생성된 클래스에 파라미터가 없는 생성자가 있어야 하는 경우, 모든 프로퍼티의 대한 기본값을 지정해야함
     */
    data class User2(val name: String = "", val age: Int = 0)

    /*
    클래스 내부에 선언된 속성
    컴파일러는 자동으로 생성된 함수에 대해 기본 생성자 내에 정의된 프로퍼티를 사용함
    생성된 구현에서 프로퍼티를 제외하려면 클래스 내에서 선언함
     */
    data class Person(val name: String) {
        var age: Int = 0
    }

    /*
    위의 클래스에서 name 프로퍼티만 toString(), equals(), hashCode(), copy()에서 사용되며, component1()만 있음
    아래 두 Person 객체에서 다른 age값을 가지지만, 같은걸로 취급됨
     */
    private class ex1 {
        fun test() {
            val person1 = Person("John")
            val person2 = Person("John")
            person1.age = 10
            person2.age = 20
            //person1 == person2: true
            println("person1 == person2: ${person1 == person2}")
            //person1 with age 10: Person(name=John)
            println("person1 with age ${person1.age}: ${person1}")
            //person2 with age 20: Person(name=John)
            println("person2 with age ${person2.age}: ${person2}")
        }
    }

    /*
    일부 속성을 변경하고 나머지 속성들은 변경이 없는 채 복사를 해야 하는경우, copy()함수를 생성함
    User클래스의 경우 copy()는 다음과 같이 선언되어 있음
    fun copy(name: String = this.name, age: Int = this.age) = User(name, age)
     */
    /*
    copy함수는 다음과 같이 사용함
     */
    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)

    /*
    데이터 클래스 및 구조화 선언
    데이터 클래스에 대해 생성된 구성요소함수( componentN() )를 사용하여 구조 해제 선언에 사용될 수 있음
     */
    fun tmp1() {
        val jane = User("Jane", 35)
        val (name, age) = jane
        println("$name, $age years of age") // prints "Jane, 35 years of age"
    }

    /*
    기본 라이브러리에서는 Pair과 Triple을 제공함.
     */
}

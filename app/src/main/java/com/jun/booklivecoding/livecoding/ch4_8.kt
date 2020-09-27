package com.jun.booklivecoding.livecoding

class ch4_8 {
    //자바와 동일하게 상속은 하나만 가능하며, 인터페이스는 다중 구현이 가능함
    //코틀린의 인터페이스는 자바와 거의 사용법이 같음
    //추상메소드는 abstract 키워드가 필요하지만 인터페이스는 생략할 수 있음
    interface Runnable {
        fun run()

        //다음과 같이 구현된 메소드를 포함할 수 있음
        fun fastRun() = println("fast run!")
    }

    //인터페이스는 다음과 같이 구현함
    class Human : Runnable {
        override fun run() {
            println("run!")
        }
    }

    fun ch2() {
        val h = Human()
        h.run()     //run!
        h.fastRun() //fast run!
    }

    //다음과 같이 상속과 인터페이스를 함께 구현할 수 있음
    open class Animal {
        fun bark() = println("bark!")
    }
    interface Eatable {
        fun eat()
    }

    class Dog : Animal(), Runnable, Eatable {
        override fun eat() {
            println("eat!")
        }

        override fun run() {
            println("run!")
        }
    }
    fun ch3() {
        val d = Dog()
        d.eat()     //eat!
        d.run()     //run!
        d.fastRun() //fast run!
        d.bark()    //bark!
    }
}

fun main() {
    ch4_8().ch3()
}
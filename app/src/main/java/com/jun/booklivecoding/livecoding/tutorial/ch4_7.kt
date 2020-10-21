package com.jun.booklivecoding.livecoding.tutorial

class ch4_7 {
    //클래스 선언
    class Person {

    }

    fun ch1() {
        //코틀린에서는 new를 사용하지 않고 다음과 같이 생성함
        val person = Person()
    }

    //빈 생성자는 다음과 같이 선언함
    class Person2(var name: String) {

    }

    //초기화 코드를 작성하려면 constructor을 사용하여 다음과 같이 선언
    class Person3 {
        constructor(name: String) {
            println(name)
        }
    }

    //init 블록을 사용하여 초기화 할수도 있음. init 블록은 클래스를 인스턴스화 할때 가장 먼저 초기화됨.
    class Person4(name: String) {
        init {
            println(name)
        }
    }

    fun ch2() {
        val p2 = Person2("test2")
        val p3 = Person3("test3")   //test3
        val p4 = Person4("test4")   //test4
    }

    //코틀린에서는 getter/setter을 프로퍼티로 대체함
    fun ch3() {
        val person = Person2("멋쟁이")
        println(person.name)    //"멋쟁이"
        person.name = "키다리"
        println(person.name)    //"키다리"
    }

    //코틀린에서는 다음과 같은 접근제한자를 사용함
    open class A() {
        //public(생략가능) : 전체공개, 아무것도 안 쓰면 기본적으로 public
        val a = 1

        //private : 현재 클래스 내부에서만 사용할 수 있음
        private val b = 2

        //protected : 상속받은 클래스에서 사용할 수 있음
        protected val c = 3

        //internal : 같은 모듈 내에서만 사용할 수 있음
        internal val d = 4
        fun printAVal() {
            println("$a $b $c $d")
        }
    }

    //A 클래스를 상속한 B
    class B : A() {
        //접근가능한 변수 : a,c,d
        fun printBVal() {
            println("${this.a} ${this.c} ${this.d}")
        }
    }

    fun ch4() {
        val a = A()
        a.printAVal()   //1 2 3 4
        println("${a.a} ${a.d}")    //1 4
        val b = B()
        b.printBVal()   //1 3 4
    }

    //코틀린에서는 기본적으로 상속이 금지됨. open 키워드를 사용하여 상속이 가능함을 알림
    //상속받을 클래스가 생성자를 가지고 있으면 다음과같이 사용함
    open class Animal(val name: String) {

    }
    class Dog(name: String) : Animal(name) {

    }
    fun ch5() {
        val d = Dog("dog!")
        println(d.name)     //dog!
    }

    //내부클래스 선언에는 inner을 사용함. 외부클래스에 대한 참조를 가지고 있음
    class OuterClass {
        var a = 10
        inner class OuterClass2 {
            fun something() {
                //inner 키워드가 없는경우 접근 불가
                println(System.identityHashCode(a)) //o
                a = 20
            }
        }
    }

    fun ch6() {
        val o = OuterClass()
        println(System.identityHashCode(o.a))   //692404036
        println(o.a)    //10
        val o2 = o.OuterClass2()
        o2.something()  //692404036
        println(o.a)    //20
    }

    //추상클래스는 다음과 같이 사용함. java와 마찬가지로 상속받는 클래스에서 메소드를 구현해야함
    abstract class C{
        abstract fun func()
        fun func2() {
            println("hello2")
        }
    }
    class D: C() {
        override fun func() {
            println("hello")
        }
    }
    fun ch7() {
        //abstract 를 선언한 클래스는 인스턴스를 만들수 없음
        //val c = C()
        val d = D()
        d.func()    //hello
        d.func2()   //hello2
    }
}

fun main() {
    ch4_7().ch7()
}
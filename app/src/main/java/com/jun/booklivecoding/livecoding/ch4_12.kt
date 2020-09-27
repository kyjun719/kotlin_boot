package com.jun.booklivecoding.livecoding

import java.util.*

class ch4_12 {
    fun ch1() {
        //확장함수 기능을 사용하여 쉽게 기존 클래스에 함수를 추가할 수 있음
        //확장 함수를 추가할 클래스에 점을 찍고 함수 이름을 작성함.
        //확장함수 내부에서는 이 객체를 this로 접근할 수 있고 이런 객체를 리시버 객체라고 함
        fun Int.isEven() = this%2==0
        val a = 5
        val b = 6
        println(a.isEven())
        println(b.isEven())
    }

    fun ch2() {
        //형변환은 to자료형() 메서드를 사용하여 형변환이 가능함
        val a = 10L
        val b = 20
        val c = a.toInt()       //Long을 Int로
        val d = b.toDouble()    //Int를 Double로
        val e = a.toString()    //Long을 String으로

        //숫자형태의 문자열을 숫자로 바꿀 때는 Integer.parseInt()를 사용함
        val intStr = "10"
        val str = Integer.parseInt(intStr)

        //일반 클래스간에 형변환을 하려면 as키워드를 사용함
        open class Animal
        class Dog: Animal()
        val dog = Dog()
        val animal = dog as Animal  //dog를 Animal형으로 변환
    }

    fun ch3() {
        //is 키워드를 사용하여 형을 체크할 수 있음
        val str="hello"
        if(str is String) {
            println(str.toUpperCase(Locale.ROOT))
        }
    }

    fun ch4() {
        //다른함수를 인수로 받거나 반환하는 함수를 고차함수 라고함
        //callback함수는 Int를 받아 아무것도 반환하지 않는 함수여야함
        fun add(x: Int, y: Int, callback: (sum: Int) -> Unit) {
            callback(x+y)
        }
        //함수는 {}로 감싸고 내부에서는 반환값을 it로 접근할 수 있음
        add(5,3, {println(it)})
    }

    //코틀린에서는 자바의 static과 같은 정적인 메서드를 만들수 있는 키워드를 제공하지 않음
    //대신 동반객체(companion object)로 이를 구현함
    class Fragment private constructor() {
        companion object {
            fun newInstance(): Fragment {
                println("created!")
                return Fragment()
            }
        }
    }
    fun ch5() {
        //기본생성자를 private으로 선언하여 기본 생성자로 인스턴스 생성 불가능
        val frag = Fragment.newInstance()
   }

    fun ch6() {
        //let은 블록에 자기자신을 인수로 전달하고 수행된 결과를 반환함
        //인수로 전달한 객체는 it으로 참조함
        //fun <T,R> T.let(block: (T) -> R):R
        val str: String? = "1234"
        val result = str?.let {
            Integer.parseInt(it)
        }
        //1234 -> 1234
        print("$str -> $result")
    }

    fun ch7() {
        //with 함수는 인수로 객체를 받고 블록에 리시버 객체로 전달함.
        //리시버 객체로 전달된 객체는 this로 접근할 수 있음. this는 생략 가능함
        //fun <T,R> with(receiver: T, block T.() -> R):R
        val str: String? = "abcd"
        if(str != null) {
            val tmp = with(str) {
                println(toUpperCase(Locale.ROOT))   //ABCD
                "1234"
            }
            println(tmp)    //1234
            println(str)    //abcd
        }
    }

    class Car(var color: String, var price: Int) {
        override fun toString(): String {
            return "${color}:${price}"
        }
    }
    fun ch8() {
        //apply() 함수는 블록에 객체 자신이 리시버 객체로 전달되고 이 객체가 반환됨
        //객체의 상태를 변화시키고 그 객체를 다시 반환할 때 주로 사용함
        //fun <T> T.apply(block: T.() -> Unit): T
        val car: Car? = Car("red",1000)
        println(car.toString()) //red:1000

        val result = car?.apply {
            car.color = "white"
            car.price = 5000
        }
        println(result) //white:5000
        println(car)    //white:5000
    }

    fun ch9() {
        //run() 함수는 익명함수처럼 사용하는 방법과, 객체에서 호출하는 방법을 모두 제공함
        //익명함수처럼 사용할 때는 블록의 결과를 전달함. 블록안에 선언된 변수는 모두 임시로 사용되는 변수
        //fun <R> run(block: () -> R): R
        val avg = run {
            val korean = 100
            val english = 80
            val math = 50
            (korean+english+math)/3.0
        }
        println(avg)    //76.66666666666667

        //객체에서 호출하는 방법은 객체를 블록의 리시버 객체로 전달하고 블록의 결과를 반환함
        //fun <T, R> T.run(block: T.() -> R): R
        val str: String? = "abcd"
        val ret = str?.run {
            println(toUpperCase(Locale.ROOT))
            toUpperCase(Locale.ROOT)
        }
        println("$str $ret")    //abcd ABCD
    }
}

fun main() {
    ch4_12().ch9()
}
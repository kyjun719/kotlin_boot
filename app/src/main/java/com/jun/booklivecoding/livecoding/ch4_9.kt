package com.jun.booklivecoding.livecoding

import java.util.*

class ch4_9 {
    fun ch1() {
        //코틀린에서는 기본적으로 null값을 허용하지 않으며, 객체의 생성과 동시에 값을 대입해야함
        val a : String = "a"
        //null을 허용하려는 경우 자료형 오른쪽에  ? 기호를 붙여줌
        val b : String? = null
    }

    fun ch2() {
        //lateinit으로 객체를 나중에 초기화 할 수 있음
        //lateinit은 다음조건에서만 사용할 수 있음
        /*
        - var 변수에서만 가능
        - null값으로 초기화할 수 없음
        - 초기화전에는 변수를 사용할 수 없음
        - Int, Long, Double, Float에서는 사용할 수 없음
         */
        lateinit var a : String
        a = "hello"
        println(a)
    }

    fun ch3() {
        //lazy는 val을 늦은초기화 할 때 사용함
        //lazy는 다음 조건에서 사용할 수 있음
        /*
        - val에서만 사용함
         */
        //val 선언뒤에 by lazy 블록에 초기화에 필요하나 코드를 작성함
        //str이 처음 호출될 때에만 초기화 블록의 코드가 실행됨
        val str: String by lazy {
            println("init!")
            "hello"
        }
        println(str)    //init!; hello
        println(str)    //hello
    }

    fun ch4() {
        //변수뒤에 !!을 추가하여 null값이 아님을 보증함
        val name: String? = "키다리"
        //에러 발생
        //val name2: String = name
        val name3: String? = name

        val name4: String = name!!
    }

    fun ch5() {
        //메소드 호출시 점 . 연산자 대신 ?. 연산자를 사용하면 null값이 아닌경우에만 호출됨
        val str1: String? = null
        val str2: String? = "abcd"

        //if문을 사용하여 다음과 같이 표현할 수 있음
        var up11 = if(str1 != null) str1.toUpperCase(Locale.ROOT) else null
        var up12 = if(str2 != null) str2.toUpperCase(Locale.ROOT)else null

        var up21 = str1?.toUpperCase(Locale.ROOT)
        var up22 = str2?.toUpperCase(Locale.ROOT)

        println("$up11 $up12")  //null ABCD
        println("$up21 $up22")  //null ABCD
    }

    fun ch6() {
        //null이 아닌 기본값을 반환하고 싶을 때는 엘비스 연산자를 사용함
        val str: String? = null
        val a = if (str != null) str else "need init!"
        val b = str?.toUpperCase(Locale.ROOT) ?: "need init!"
        println("$a $b")    //need init! need init!
    }
}

fun main() {
    ch4_9().ch6()
}
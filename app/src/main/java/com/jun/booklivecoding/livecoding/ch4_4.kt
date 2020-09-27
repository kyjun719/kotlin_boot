package com.jun.booklivecoding.livecoding

class ch4_4() {
    fun ch1(){
        //변수
        var a : Int = 10
        //상수
        val b : Int = 20
        var c = 30
        val d = 40
        c=50
        //e=60  //상수는 재할당하지 못함
    }

    fun ch2() {
        //str은 변수명으로 String타입이라고 선언
        //함수의 반환형이 없을때 Unit
        fun helloWorld(str: String): Unit {
            println(str)
        }
        helloWorld("hello!")
    }
}

fun main() {
    ch4_4().ch1()
}
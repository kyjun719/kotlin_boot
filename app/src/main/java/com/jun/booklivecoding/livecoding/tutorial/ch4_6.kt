package com.jun.booklivecoding.livecoding.tutorial

class ch4_6() {
    fun ch1() {
        //if
        //실행할 문장이 한줄이면 블록을 생략할 수 있음
        val a = 10
        val b = 20
        var max = a
        if(a<b) max=b
        println("max is $max")

        //if else 문은 다음과 같이 사용함
        if(a>b) {
            max = a
        } else {
            max = b
        }
        println("max is $max")

        //다음과 같이 식(expression)으로 사용할 수 있음
        max = if(a>b) a else b
        println("max is $max")
    }

    fun ch2() {
        //when은 switch에 대응함. 콤마나 in연산자로 값의 범위를 지정하고, else로 나머지 처리
        val x = 1
        when (x) {
            //값 하나
            1 -> println("x == 1")
            //값 여러개
            2,3 -> println("x == 2 or x == 3")
            //값 범위
            in 4..7 -> println("x in 4~7")
            !in 8..10 -> println("x not in 8~10")
            //나머지
            else -> {
                println("x is no 1 or 2")
            }
        }

        //if문과 마찬가지로 식처럼 사용할 수 있음
        val strNum = when(x%2) {
            0-> "짝"
            else -> "홀"
        }
        println("$x is $strNum")    //1 is 홀

        //결과를 함수의 반환값으로 사용할 수 있음
        fun isEven(num: Int) : String = when(num%2) {
            0 -> "짝"
            else -> "홀"
        }
        println("$x is "+isEven(x)) //1 is 홀
    }

    fun ch3() {
        //java의 foreach와 비슷함
        val arr = arrayOf(1,2,3,4,5)
        for(num in arr) {
            print("$num ")  //1 2 3 4 5
        }
        println()

        //증가범위는 ..을 사용함
        for(i in 1..3) {
            print("$i ")    //1 2 3
        }
        println()

        //step 키워드로 증감의 간격 조정
        for(i in 0..10 step 2) {
            print("$i ")    //0 2 4 6 8 10
        }
        println()

        //감소범위는 downTo
        for(i in 10 downTo 0 step 2) {
            print("$i ")    //10 8 6 4 2 0
        }
        println()
    }

    fun ch4() {
        //while
        var x = 10
        //10 9 8 7 6 5 4 3 2 1 0
        print("$x ")
        while(x>0) {
            x--
            print("$x ")
        }
        println()

        //do while
        x = 10
        //9 8 7 6 5 4 3 2 1 0
        do {
            x--
            print("$x ")
        } while(x>0)
    }
}

fun main() {
    ch4_6().ch4()
}
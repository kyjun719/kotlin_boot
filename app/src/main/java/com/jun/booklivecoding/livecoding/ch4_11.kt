package com.jun.booklivecoding.livecoding

import android.widget.Button

class ch4_11 {
    fun ch1() {
        fun add1(x: Int, y: Int): Int {
            return x+y
        }
        //반환 자료형, 블록, return을 생략할 수 있음
        fun add2(x: Int, y: Int) = x+y

        //람다식은 다음과 같이 중괄호에 싸여있으며, 인수목록을 나열하고 -> 이후에 본문이 위치함
        val add3 = {x: Int, y: Int -> x+y}
        println(add1(1,2))  //3
        println(add2(1,2))  //3
        println(add3(1,2))         //3
    }

//    추상메서드 하나를 인수로 사용할 땐 함수를 인수로 전달하면 편함
//    자바로 작성된 메서드가 하나인 인터페이스를 구현할 때 함수를 작성할 수 있음(SAM 변환)
    //자바에서 작성한 인터페이스일 때만 동작함
//    버튼의 클릭 이벤트 리스너는 다음과 같이 표현할 수 있음
    /*
    button.setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {
            //클릭시 처리
        }
    })
     */

    //구현해야할 인터페이스에 구현해야 할 메서드가 하나뿐일 경우 람다식으로 변경할 수 있음
    /*
    button.setOnClickListener({v: View? ->
        //클릭시 처리
    })
     */

    //메서드 호출시 맨 마지막에 전달되는 인수가 람다식인 경우 람다식을 괄호 밖으로 꺼낼 수 있음
    /*
    button.setOnClickListener() {v: View? ->
        //클릭시 처리
    }
     */

    //람다가 어떤 메소드의 유일한 인수인 경우에는 메서드의 괄호 생략 가능함
    /*
    button.setOnClickListener {v: View? ->
        //클릭시 처리
    }
     */

    //컴파일러가 자료형을 추론하는 경우 자료형을 생략할 수 있음
    /*
    button.setOnClickListener {v ->
        //클릭시 처리
    }
     */

    //v라는 인수를 사용하지 않는경우 _ 기호로 변경하여 사용하지 않도록 할 수 있음
    /*
    button.setOnClickListener {_ ->
        //클릭시 처리
    }
     */

    //람다식에서 인수가 하나인 경우 생략하고 it으로 접근할 수 있음
    /*
    button.setOnClickListener {
        it.visibility = View.GONE
    }
     */
}

fun main() {
    ch4_11().ch1()
}
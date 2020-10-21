package com.jun.booklivecoding.livecoding.tutorial

class ch4_5() {
    fun ch1() {
        //기본 자료형
        //Double : 64비트 부동소수점
        //Float : 32비트 부동소수점
        //Long : 64비트 정수
        //Int : 32비트 정수
        //Short : 16비트 정수
        //Byte : 8비트 정수
        val a = 10      // Int
        val b = 10L     // Long
        val c = 10.0    // Double
        val d = 10.0f   // Float
    }

    fun ch2() {
        //문자형
        //String : 문자열
        //Char : 하나의 문자
        val str = "안녕하세요"   // String
        val char = '안'         // Char
        //여러줄의 문자열은 """ 를 리터럴로 사용함
        val str2 = """오늘은
날씨가 좋습니다.
빨래를 합시다
"""

        //문자열 비교는 == , 오브젝트 비교는 ===으로 함
        val str3 = "hello"
        if(str3 == "hello") {
            println("안녕하세요")
        } else {
            println("안녕 못해요")
        }

        //+기호로 문자열을 연결할 수 있음
        val str4 = "안녕"
        println(str4 + "하세요")   //안녕하세요
        //$기호를 사용하여 변수를 문자열 안에 넣을수 있음
        println("$str4 하세요")    //안녕 하세요
        println("${str4}하세요")   //안녕하세요
    }

    fun ch3() {
        //arrayOf 메소드를 사용하여 배열의 생성 및 초기화가 가능함
        val arr: Array<Any> = arrayOf(1,2.0f,3L,"4")
        //배열을 출력할 때는 contentToString()을 사용함
        println(arr.contentToString())  //[1, 2.0, 3, 4]

        //컴파일러가 자료형을 유추할 수 있는경우 자료형을 생략할 수 있음
        val intArr = arrayOf(1,2,3,4)

        //배열의 대괄호를 사용하여 변수에 접근할 수 있음
        println(intArr.contentToString())   //[1, 2, 3, 4]
        intArr[0]=5
        println(intArr.contentToString())   //[5, 2, 3, 4]
    }
}


fun main() {
    ch4_5().ch3()
}
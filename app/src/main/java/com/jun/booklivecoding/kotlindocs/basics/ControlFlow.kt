package com.jun.booklivecoding.kotlindocs.basics

import java.lang.Integer.parseInt

//if
fun ifTest() {
    //코틀린에는 3항연산자가 없어 if문으로 처리해야함
    val a = 10
    val b = 20

    //Traditional
    var max = a
    if (a<b) max=b

    //with else
    var max2: Int
    if (a>b) {
        max2=a
    } else {
        max2=b
    }

    // As expression
    val max3 = if(a>b) a else b

    //As block expression
    //블록내 코드들을 실행후 맨 마지막 값을 반환함
    val max4 = if(a>b) {
        print("choose a")
        a
    } else {
        print("choose b")
        b
    }
}

//when
fun whenTest() {
    //값을 만들면 expression, 값을 만들지 않으면 statement
    //표현식(expression)으로 사용되면 만족한 분기의 값이 전체 표현식의 값이 됨
    //문(statetment)으로 사용되면 개별 분기의 값은 무시됨
    //else는 아무조건도 해당하지 않았을 때 사용되는 값으로, 표현식의 경우 필수
    val a = 1
    val s = "1"
    val b = when(a) {
        1 -> {
            print("in here!")
            10
        }
        2 -> 20
        else -> 40
    }

    //콤마로 조건 묶기
    when(a) {
        0,1 -> print("a == 0 or a == 1")
        else -> print("otherwise")
    }

    //표현식도 분기조건으로 사용 가능함
    when(a) {
        parseInt(s) -> print("s encodes a")
        else -> print("s not encodes a")
    }

    //범위 또는 컬렉션내의 존재여부를 조건으로 사용 할수도 있음
    //!in 을 사용하여 범위 밖을 조건으로 사용할 수 있음
    val arr = arrayOf(1,2,3)
    when(a) {
        in 1..10 -> print("a in the range")
        in arr -> print("a in the array")
        !in 10..20 -> print("a is out of range")
        else -> print("nothing")
    }

    //is 또는 !is로 변수의 타입을 조건으로 사용할 수 있음
    val tmp = when(a) {
        is Int -> a > 10
        else -> false
    }

    //아무런 인자가 없는경우 조건은 boolean을 사용해야함. 조건중 true인 값을 사용함
    //홀수와 짝수를 계산하는 확장함수를 Int 자료형에 추가함
    val isOdd: Int.() -> Boolean = {
        this%2==1
    }
    val isEven: Int.() -> Boolean = {
        !this.isOdd()
    }

    val x = 10
    when {
        x.isOdd() -> print("x is odd")
        x.isEven() -> print("x is even")
        else -> print("nothing")
    }

    //1.3에서는 다음과 같이 변수를 캡쳐하여 사용할 수 있음
    /*
    fun Request.getBody() =
        when (val response = executeRequest()) {
            is Success -> response.body
            is HttpError -> throw HttpException(response.status)
        }
     */
}

fun forTest() {
    //iterator을 지원아는 어떤것이든 for문에서 사용할 수 있음(foreach)
    val arr = arrayOf(1,2,3)
    for(item in arr) print(item)

    //block 사용도 가능함
    for(item in arr) {
        print(item)
    }

    //for문에서 사용할 수 있는 iterator은 다음사항들을 만족해야함
    /*
    - 타입을 반환하는 iterator()을 멤버로 가지고 있거나 확장하였는가
    - next()를 멤버로 가지고 있거나 확장하였는가
    - Boolean을 반환하는 hasNext()를 멤버로 가지고 있거나 확장하였는가
     */
    //위의 세가지 함수는 operator로 표시되어야 함
    //operator : 연산자를 오버로딩하거나 규칙을 구현하는 기능

    //범위도 사용 가능함
    for(i in 1..3) {
        println(i)
    }

    //downTo를 사용하여 변수를 감소시킬수도 있고, step을 사용하여 증가 또는 감소되는 폭을 조정할 수 있음
    for(i in 6 downTo 0 step 2) {
        println(i)
    }

    //범위 또는 배열은 iterator 객체를 만들지 않는 인덱스 기반 루프로 컴파일됨
    //배열이나 리스트를 인덱스와 같이 이터레이트 하려면 다음과 같이 해야함
    //indices : 배열의 유효한 인덱스 범위를 반환함
    for(i in arr.indices) {
        println(arr[i])
    }

    //withIndex를 사용함
    for((index, value) in arr.withIndex()) {
        println("$index $value")
    }
}

fun whileTest() {
    var x = 10
    //while 문
    while(x>10) {
        x--
    }

    x=10
    //do..while문
    do{
        x--
    } while(x>10)

    //break와 continue도 사용 가능함
    x=10
    var cnt = 0
    while(true) {
        if(x<=0) break
        x--
        if(x%2 == 0) {
            continue
        }
        //x가 9,7,5,3,1일때 도달함
        cnt++
    }
    //0, 5
    print("$x , $cnt")
}

fun main() {
    whileTest()
}
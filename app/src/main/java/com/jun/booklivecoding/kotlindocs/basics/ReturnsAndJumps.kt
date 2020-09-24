package com.jun.booklivecoding.kotlindocs.basics

//코틀린에는 세가지 구조적 점프 표현식이 있음
// return : 인접한 함수나 익명함수에서 리턴함
// break : 인접한 루프를 종료함
// continue : 인접한 루프의 다음단계로 넘어감

//코틀린의 모든 표현식은 label로 마킹될 수 있음.
fun test() {
    //loop label을 선언함
    var cnt = 0
    loop@ for(i in 1..100) {
        for(j in 1..100) {
            if(j == 10) {
                //loop break
                break@loop
            }
            cnt++
        }
    }
    //j==10일때 종료하므로 cnt는 9번 증가됨
    print("$cnt")
}

//return은 인접한 외부 함수를 반환함
fun foo1() {
    //listOf() : list 초기화
    //forEach : iterator의 각 아이템으로 for문 순회
    //람다식에서 파라미터가 하나인경우 it으로 사용할 수 있음
    listOf(1,2,3,4,5).forEach {
        if (it == 3) return
        print(it)
    }
    println("this point is unreachable")
}

//람다식만 종료하려는 경우 라벨을 사용하여 종료함
fun foo2() {
    listOf(1,2,3,4,5).forEach lit@{
        if (it == 3) return@lit
        print(it)
    }
    println("this point is reachable")
    //출력결과 : 1245this point is reachable
}

//암시적 라벨을 사용하여 종료 할 수 있음
fun foo3() {
    listOf(1,2,3,4,5).forEach {
        if(it==3) return@forEach
        print(it)
    }
    println("this point is reachable")
    //출력결과 : 1245this point is reachable
}

//익명함수도 종료할 수 있음
fun foo4() {
    listOf(1,2,3,4,5).forEach(fun(value:Int ) {
        if(value==3) return
        print(value)
    })
    println("this point is reachable")
    //출력결과 : 1245this point is reachable
}

//break 기능은 다음과 같이 구현할 수 있음
fun foo5() {
    run loop@{
        listOf(1,2,3,4,5).forEach {
            if(it==3) return@loop
            print(it)
        }
    }
    println("this point is reachable")
    //출력결과 : 12this point is reachable
}

//label과 함께 값을 반환할 수도 있음
fun foo6() {
    var cnt = 0
    val a = abc@{
        for(i in 1..100) {
            //@abc 라벨을 종료하고 라벨에 대한 리턴값을 100으로 함
            if (i==10) return@abc 100
            cnt++
        }
    }
    val tmp = a()
    //100 9
    print("$tmp $cnt")
}

fun main() {
    foo6()
}
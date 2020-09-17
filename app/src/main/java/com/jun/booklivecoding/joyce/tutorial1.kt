package com.jun.booklivecoding.joyce

import java.util.*

fun main() {
//    Ch1().run()
//    Ch2().test()
//    Ch3().test()
//    Ch4().test()
//    Ch6().arrayTest()
//    Ch7().iterTest()
    Ch7().nullcheck()
}

class Ch1 {
    //1. fun
    fun helloWorld() {
        println("hello world")
    }

    //param과 return type이 있는 fun
    //param 이름 먼저 씀
    fun add(a : Int, b : Int) : Int {
        return a+b
    }

    fun run() {
        helloWorld()
        //2+3 is 5
        println("2+3 is ${add(2,3)}")
    }
}

class Ch2 {
    //2. val vs var
    //val : constant
    //var : variable
    fun test() {
        val a=10
        var b=10
        var c : Int = 30
        //val을 변경하려는 경우 에러 발생
        //a=20
        b=20
        //10
        println(a)
        //20
        println(b)
        //30
        println(c)

        val d : Int
        d = 40
        //40
        println(d)
    }
}

class Ch3 {
    //3. String template
    fun test() {
        println("a is abcd")

        val a="abcd"
        //a is abcd
        println("a is ${a}")

        val b="efgh"
        //a+b is abcdefgh
        println("a+b is ${a+b}")

        //달러 문자열은 백슬래시 붙여서 사용(\$)
        val tmp = 4*1200
        //$4 is about 4800
        println("\$4 is about ${tmp}")
    }
}

class Ch4 {
    //4. 조건식
    //코틀린은 삼항연산자가 없음
    fun max1(a : Int, b : Int) : Int {
        if(a>b){
            return a
        } else {
            return b
        }
    }

    fun max2(a : Int, b : Int) : Int = if(a>b) a else b

    //when은 switch문
    fun checkScore(score : Int){
        when(score){
            0 -> println("this is 0")
            1 -> println("this is 1")
            2,3 -> println("this is 2 or 3")
            else -> println("this is more")
        }

        val b = when(score){
            0 -> "0"
            1 -> "1"
            else -> "?"
        }
        println("b is ${b}")

        when(score){
            //범위가 겹치는 경우 위에 쓴 조건부터 걸림
            in 80..100 -> println("good")
            in 50..80 -> println("not bad")
            else -> println("bad")
        }
    }
    //리턴형의 경우 else를 꼭 써야함
    //when의 범위를 다음꺼 이어서 하면 어떻게 되는가?

    fun test() {
        //3,4? 4 4
        println("3,4? ${max1(3,4)} ${max2(3,4)}")
        checkScore(80)
    }
}

//5. expression vs statement
//값을 만들면 expression
//값을 만들지 않으면 statement
//함수는 기본적으로 Unit을 반환하므로 expression
//자바의 void는 statement

class Ch6 {
    //6. Array, List, MutableList
    //Array는 초기 크기를 할당해야함

    fun arrayTest() {
        val a = arrayOf(1,2,3)
        val b = listOf(1,2,3)

        //a is [Ljava.lang.Integer;@24d46ca6
        println("a is $a")
        //a is 1, 2, 3
        println("a is ${a.joinToString()}")
        //b is [1, 2, 3]
        println("b is $b")

        //AnyType으로 지정됨
        val c = arrayOf(1,"2",3.123f)
        //c is [Ljava.lang.Object;@65b54208
        println("c is $c")
        //c is 1, 2, 3.0
        println("c is ${c.joinToString()}")

        //list는 get은 되지만 add는 안됨
        //MutableList는 둘다됨

        //arraylist내부의 값은 바뀌지만 arraylist자체은 바뀌지 않음
        val arraylist = arrayListOf<Int>()
        arraylist.add(10)
        arraylist.add(20)
        arraylist[0] = 20
        //arraylist is [20, 20]
        println("arraylist is $arraylist")
    }
}

class Ch7 {
    //7. 반복문 for, while
    fun iterTest() {
        val a = arrayListOf("a","b","c")
        //a의 아이템을 하나씩 출력
        for(tmp in a){
            println(tmp)
        }

        //아이템의 인덱스와 항목을 출력
        for((index, name) in a.withIndex()) {
            println("idx${index} is ${name}")
        }

        var sum=0
        for(i in 1..10) {
            sum+=i
            //1 3 6 10 15 21 28 36 45 55
            print("$sum ")
        }
        println()

        for(i in 1 until 10){
            //10을 포함하지 않음
            sum+=i
            //1 3 6 10 15 21 28 36 45
            print("$sum ")
        }
        println()

        //1~10을 2씩 건너서
        sum=0
        for(i in 1..10 step 2){
            //1,3,5,7,9,10 출력
            println(i)
        }

        //10~1
        for(i in 10 downTo 1) {
            //10,9,8,...1 출력
            println(i)
        }

        var idx=0
        while(idx<10){
            //idx 0~9까지 출력
            println("idx is ${idx}")
            idx++
        }
    }

    //7. NonNull, Nullable
    fun nullcheck() {
        //nullable의 경우 타입을 명시해야함
        val b : String? = null

        val name="1234"
        val upStr = name.toUpperCase(Locale.ROOT)
        //해당변수가 null인지 아닌지 모르기 때문에 컴파일 실패
        //var nullUpStr = b.toUpperCase()
        //b가 null일경우 nullUpStr도 null이고, null이 아닐경우 함수를 실행함
        val nullUpStr = b?.toUpperCase(Locale.ROOT)
        //1234 null
        println("${upStr} ${nullUpStr}")

        //변수가 null일경우 디폴트값을 주기위해아래 연산자를 사용함, 엘비스연산자
        //?:
        var lastName1 : String? = null
        val lastName2 : String? = "LN"
        val fullName1 = name+" "+(lastName1?:"no last name")
        val fullName2 = name+" "+(lastName2?:"no last name")
        //1234 no last name
        println(fullName1)
        //1234 LN
        println(fullName2)

        //변수가 null이 아님을 보장할때 아래연산자 사용
        //!!
        var st : String? = null
        try {
            //!!연산자를 써서 st가 null이 아님을 보장하였지만 null이므로 익셉션 일어남
            val et1 = st!!
            println(et1.toUpperCase(Locale.ROOT))
        } catch (e:Exception) {
            e.printStackTrace()
        }
        st="not null"
        val et2 = st
        //NOT NULL
        println(et2.toUpperCase(Locale.ROOT))


        //let : 해당 필드가 널이 아닐경우 실행
        val tmp : String? = null
        //tmp를 람다식 내부로 옮겨서 실행
        tmp?.let{
            //tmp가 null이므로 실행되지 않음
            println("tmp is ${tmp}")
        }

        val tmp2 : String? = "1234"
        tmp2?.let{
            //tmp2가 null이 아니므로 실행됨
            println("tmp is ${tmp2}")
        }
    }
}

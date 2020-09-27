package com.jun.booklivecoding.livecoding

class ch4_10 {
    fun ch1() {
        //읽기 전용 리스트는 listOf() 메서드로 작성할 수 있음
        val foods1: List<String> = listOf("1","2","3")
        //형 추론이 가능한 경우 자료형을 생략할 수 있음
        val foods2 = listOf("1","2","3")

        //요소를 변경하는 리스트를 작성할 때는 mutableListOf() 메서드를 사용함
        //특정요소에 접근할 때는 대괄호 안에 요소번호로 접근할 수 있음
        val foods3 = mutableListOf("1","2","3")
        println(foods3)     //[1, 2, 3]

        foods3.add("4")
        println(foods3)     //[1, 2, 3, 4]

        foods3.removeAt(0)
        println(foods3)     //[2, 3, 4]

        foods3[0] = "0"
        println(foods3)     //[0, 3, 4]
        println(foods3[0])  //0
    }

    fun ch2() {
        //mapOf()로 읽기 전용맵을 만들수 있고, mutableMapOf()로 수정가능한 맵을 만들 수 있음
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        val mutableMap = mutableMapOf("A" to "a", "B" to "b", "C" to "c")

        //덮어쓰기
        mutableMap["A"] = "aa"
        //추가
        mutableMap["D"] = "d"

        //맵 전체는 다음과 같이 탐색할 수 잇음
        for((k,v) in map) {
            println("$k -> $v")     //a -> 1; b -> 2; c -> 3
        }
    }

    fun ch3() {
        //set은 중복되지 않는 요소들로 구성된 자료구조
        //setOf()로 읽기전용 집합을, mutableSetOf()로 수정 가능한 집합을 생성함
        val set = setOf("1","2","3")

        val mutableset = mutableSetOf("1","2","3")
        mutableset.add("1")
        println(mutableset) //[1, 2, 3]

        mutableset.add("4")
        println(mutableset) //[1, 2, 3, 4]

        mutableset.remove("1")
        println(mutableset) //[2, 3, 4]

        println(mutableset.size)    //3
        println(mutableset.contains("2"))   //true
    }
}

fun main() {
    ch4_10().ch3()
}
package com.jun.booklivecoding.joyce

//8. 클래스
//파일이름과 클래스이름이 달라도됨
class Test {
    fun printTest() {
        println("this is test")
    }
}

class Test2 constructor(name : String){
    val name = name
}

class Test3(val name : String){
    //Test2에서 일어나는 warning을 수정한 클래스
}

class Test4(val name : String = "default"){
}

class Test5{
    var name:String? = null
    init{
        this.name="test5!!"
    }
}

//init이 주생성자이므로 constructor은 나중에 실행됨
class Test6(val name : String){
    var age: Int = 0
    var birth:String? = null
    //기본생성자를 오버로딩함
    constructor(name : String, age : Int) : this(name) {
        this.age = age
        println("in constructor1")
    }
    //위의 생성자를 오버로딩함
    constructor(name: String, age: Int, birth: String): this(name,age) {
        this.birth=birth
        println("in constructor2")
    }
    init {
        //init 블록은 constructor보다 먼저 실행됨
        println("in init!")
    }
}

//상속을 받을 클래스는 같은파일내 있어도 open 키워드가 없으면 상속받지 못함
open class Human(val name: String="default"){
    init {
        println("in human init!")
    }

    open fun eatCake(){
        println("eat cake!")
    }
}

//부모클래스의 생성자를 써줘야함
class Korean : Human(){
    override fun eatCake() {
        super.eatCake()
        println("korean eat cake22")
    }
}

//부모클래스의 생성자를 사용함
class Korean2(name: String) : Human(name){
    override fun eatCake() {
        super.eatCake()
        println("korean eat cake22")
    }
}

fun main() {
    val test = Test()
    //Test 클래스의 printTest 실행
    test.printTest()

    val test2 = Test2("test2222")
    //name is test2222
    println("name is ${test2.name}")

    val test3 = Test3("test3333")
    //name is test3333
    println("name is ${test3.name}")

    val test4 = Test4()
    //생성자에 값을 넣어주지 않아서 name에 선언한 기본값이 들어감
    //name is default
    println("name is ${test4.name}")

    val test41 = Test4("test4444")
    //생성자에 값을 넣어주어서 name에 값이 들어감
    //name is test4444
    println("name is ${test41.name}")

    val test5 = Test5()
    //init 블록에서 name을 초기화 시켜주므로 해당값으로 들어감
    //name is test5!!
    println("name is ${test5.name}")

    //init 블록만 실행됨
    val test61 = Test6("test6666")
    //init -> constructor1이 실행됨
    val test62 = Test6("test6666",10)
    //init -> constructor1 -> constructor2이 실행됨
    val test63 = Test6("test6666",10,"1234")

    //test6666, 0, null
    println("${test61.name}, ${test61.age}, ${test61.birth}")
    //test6666, 10, null
    println("${test62.name}, ${test62.age}, ${test62.birth}")
    //test6666, 10, 1234
    println("${test63.name}, ${test63.age}, ${test63.birth}")

    //Korean 클래스는 Human 클래스를 상속받았으므로 Human클래스의 init 블록이 실행됨
    val korean = Korean()
    //default
    println(korean.name)
    //Korean 클래스에서 오버라이딩한 eatCake 함수가 실행됨
    korean.eatCake()

    val korean2 = Korean2("korean!")
    //부모클래스의 생성자를 사용하여 name필드는 생성자에서 입력한 값이 들어감
    //korean!
    println(korean2.name)
    //오버라이딩한 함수에서 부모의 eatCake로 호출하므로 부모의 eatCake와 오버라이딩한 내용이 같이 출력됨
    //eat cake!
    //korean eat cake22
    korean2.eatCake()
}
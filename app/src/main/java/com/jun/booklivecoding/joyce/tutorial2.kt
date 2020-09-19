package com.jun.booklivecoding.joyce

fun main() {
//    Tutorial2.Ch1().test()
//    Tutorial2.Ch1().test2()
//    Tutorial2.Ch1().test3()
//    Tutorial2.Ch1().test4()
//    Tutorial2.Ch2().test()
//    Tutorial2.Ch3().test()
    Tutorial2.Ch4().test()
}

class Tutorial2 {
    class Ch1 {
        //1. 람다(0:40)
        // 람다는 value처럼 다룰수 있는 익명함수
        // 1) 메소드의 파라미터로 넘겨줄 수 있음 fun maxBy(a: Int)
        // 2) 리턴값으로 사용될 수 있음

        // 람다의 기본정의
        // {var, val} lambdaName: Type = {arglist -> codebody}

        //Int를 받고 Int를 반환하기로 선언하였기 때문에 number에는 별도로 타입을 명시하지 않아도됨(타입추론)
        // 입력 또는 출력의 한곳에 형을 써줘야 자동추론이 가능함
        fun test() {
            //input은 Int, output은 Int
            //입력값의 선언을 없애면 타입 추론이 불가능함
            //val square= {number -> number*number}// 에러발생
            val square: (Int) -> (Int) = {number -> number*number}
            //val square = {number: Int -> number*number}//가능
            //100
            println(square(10))

            //람다에서는 코드 마지막줄이 리턴값임
            //입력과 출력형을 선언하지 않아도 자동추론됨
            val numAge = {name: String, age: Int ->
                "name is ${name}, ${age} old"
            }
            //name is test, 12 old
            println(numAge("test",12))
        }

        //1.2 확장함수(7:56)
        fun test2() {
            //기존의 클래스에 기능을 추가할 때 사용함
            //String의 확장함수로 pizza를 만듦
            val pizza: String.() -> String = {
                //this는 해당 함수를 호출하는 String객체를 가리킴
                this+"Pizza is great"
            }

            val a = "alice"
            val b = "bob"
            //alicePizza is great
            println(a.pizza())
            //bobPizza is great
            println(b.pizza())
            //I am test and 20 years old
            println(extendString("test", 20))
        }

        fun extendString(name: String, age: Int): String {
            val introduce: String.(Int) -> String = {
                //파라미터가 하나인경우 it으로 사용할 수 있음
                "I am ${this} and ${it} years old"
            }
            return name.introduce(age)
        }

        //1.3 람다의 리턴(13:50)
        fun test3() {
            val calcGrade: (Int) -> String = {
                when(it) {
                    in 0..40 -> "fail"
                    in 41..70 -> "pass"
                    in 70..100 -> "perfect"
                    //else가 없을때 when에서 에러가 발생함
                    else -> "fail"
                }
                //위에서 else문을 쓰지 않아도 반환만 하면 에러가 발생하지 않음
                //"fail"
            }
            //perfect
            println(calcGrade(90))
            //fail
            println(calcGrade(1000))
        }

        //1.4 람다를 표현하는 2가지 방법(16:54)
        fun test4() {
            val lambda = {number:Double ->
                number==4.3213
            }
            //false
            println(invokeLambda(lambda))

            //바로 중괄호를 사용하는것을 람다 리터럴이라고함
            //여기서 it은 아래 함수에서 lambda 파라미터로 넣은 5.2343이됨
            //true
            println(invokeLambda { it>3.22 })
            //마지막 파라미터가 람다일 경우 소괄호를 생략하고 바로 람다식을 적을 수 있음


        }
        //Double을 받아서 Boolean을 반환하는 람다를 받음
        //invokeLambda의 리턴값은 Boolean
        fun invokeLambda(lambda: (Double) -> Boolean): Boolean {
            return lambda(5.2343)
        }

        /*
        Button의 onClickListener의 경우 다음과 같이 두가지 방식으로 쓸 수 있음
        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                //to do...
            }
        }

        //아래와 같이 인터페이스를 가져오려면 다음과 같은 조건이 필요함
        //1. Kotlin interface가 아닌 자바 인터페이스여야함
        //2. 그 인터페이스에서는 딱 하나의 메소드만 가져야함함
       button.setOnClickListener {
            //to do...
        }
         */
    }

    class Ch2 {
        //2. Data class(27:03)
        //Data class : 데이터를 담는 클래스
        // toString(), hashCode(), equals(), copy()를 컴파일러가 자동으로 생성해줌
        //하나의 파일에 여러개의 데이터 클래스를 만들 수 있음
        data class Ticket(val company : String, val name : String, var date : String, var seat : Int)
        class TicketNormal(val company : String, val name : String, var date : String, var seat : Int)

        fun test() {
            val ticketA = Ticket("korean","test","2020-02-10",16)
            val ticketB = TicketNormal("korean","test","2020-02-10",16)

            //Ticket(company=korean, name=test, date=2020-02-10, seat=16)
            println(ticketA)
            //com.jun.booklivecoding.joyce.Tutorial2$TicketNormal@61bbe9ba
            println(ticketB)
        }
    }

    class Ch3 {
        //3. companion object(34:06)
        //자바의 static 키워드 대신 사용하는것으로 정적 메소드나 정적 변수를 선언할 때 사용함

        //private constructor을 사용하면 다른곳에서는 생성자를 사용하지 못함
        class Book private constructor(val id : Int, val name : String) {
            companion object {

                fun create() = Book(0,"book")
            }
        }

        class Book2 private constructor(val id : Int, val name : String) {
        }

        class Book3 private constructor(val id : Int, val name : String) {
            //companion에 이름 변경과 인터페이스 사용이 가능함
            companion object BookFactory : IdProvider {
                //companion에는 변수 선언도 가능함, static으로 선언됨
                val myBook = "My Book"

                fun create() = Book3(getId(), myBook)

                //해당 함수는 static으로 선언됨
                override fun getId(): Int {
                    return 444
                }
            }
        }

        interface IdProvider {
            fun getId() : Int
        }

        fun test() {
            val book1 = Book.create()
            //0 book
            println("${book1.id} ${book1.name}")
            //Book2 클래스는 생성자를 private으로 선언하여서 외부에서 사용하지 못함
            //val book2 = Book2()

            //My Book 444
            //companion object에 해당 필드들을 선언하였으므로 static처럼 접근이 가능함
           println("${Book3.BookFactory.myBook} ${Book3.BookFactory.getId()}")
            //해당 클래스에 companion object는 하나만 있으므로 생략가능함
            println("${Book3.myBook} ${Book3.getId()}")

        }
    }

    class Ch4 {
        //4.Object(40:39)
        //실행될 때 딱 한번 만들어짐. Singleton 패턴
        object CarFactory {
            val cars = mutableListOf<Car>()
            fun makeCar(horsePower: Int) : Car {
                val car = Car(horsePower)
                cars.add(car)
                return car
            }
        }
        data class Car(val horsePower : Int)

        fun test() {
            val car1 = CarFactory.makeCar(10)
            val car2 = CarFactory.makeCar(200)
            //2 >> [Car(horsePower=10), Car(horsePower=200)]
            println("${CarFactory.cars.size} >> ${CarFactory.cars}")
        }
    }
}

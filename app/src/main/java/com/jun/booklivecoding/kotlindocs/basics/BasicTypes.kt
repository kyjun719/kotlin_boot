package com.jun.booklivecoding.kotlindocs.basics

/* number */
//명시적으로 long을 쓰고싶으면 L을 붙여줌
//Byte : 8bits, -128~128
//Short : 16bits, -32767~32767
//Int : 32bits, -2^31~2^31-1
//Long : 64bits, -2^63~2^63-1
val one = 1 // Int
val threeBillion = 3000000000 // Long
val oneLong = 1L // Long
val oneByte: Byte = 1

//Float	: 32bits, 24 sigbits, 8 expbits, 6-7 decimal digits
//Double : 64bits, 53 sigbits, 11 expbits, 15-16 decimal digits
//소수일경우 알아서 double로 사용, float으로 써야할 경우 f 또는 F를 붙임
val pi = 3.14 // Double
val e = 2.7182818284 // Double
val eFloat = 2.7182818284f // Float, actual value is 2.7182817

//다른 언어와 달리 숫자형 자료간 암시적 변환이 안됨
fun conversionTest() {
    fun printDouble(d: Double) { print(d) }

    val i = 1
    val d = 1.1
    val f = 1.1f

    printDouble(d)
//    printDouble(i) // Error: Type mismatch
//    printDouble(f) // Error: Type mismatch
}

/* 리터럴 상수 */
val dec = 123
val hexDec = 0x0F //hex
val bin = 0b00001011 //bin
val doubleFP = 123.5e10 // exp expression
//underscore 사용가능
val decBig = 1234_5678_123_4_5678L
val hexBig = 0xBE_EF_FE_ED
val binBig = 0b1101_1111_11101111


/* 표현 */
//숫자가  nullable이거나 제네릭스가 포함된 경우가 아니면 Java 플랫폼에서 숫자는 JVM 기본 유형으로 물리적으로 저장됨.
//nullable이거나 제네릭스가 포함된 경우 boxing되어 처리됨
//포장된(boxing) 숫자는 반드시 동일성을 보장하지 않음
//https://iosroid.tistory.com/56
fun eqTest1() {
    //-128~127을 미리 캐싱하여 a는 새로 생성하지 않음
    val a: Int = 100
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(System.identityHashCode(boxedA)) // 1414644648
    println(System.identityHashCode(anotherBoxedA)) // 1414644648

    //999는 범위를 넘어 새로운 인스턴스를 생성하게됨. 따라서 boxing 처리 되어 동일성을 보장하지 않음
    val b: Int = 999
    val boxedB: Int? = b
    val anotherBoxedB: Int? = b
    println(System.identityHashCode(boxedB)) // 1995265320
    println(System.identityHashCode(anotherBoxedB)) // 746292446
    println(boxedA === anotherBoxedA) // true
    println(boxedB === anotherBoxedB) // false
    println(boxedB == anotherBoxedB) // true
}

/* 명시적 변환 */
fun explicitConvTest() {
    val b: Byte = 1 // OK, literals are checked statically
    //val i: Int = b // ERROR
    val b1 : Byte = b.toByte()
    val s : Short = b.toShort()
    val i: Int = b.toInt()
    val l : Long = b.toLong()
    val f : Float = b.toFloat()
    val d : Double = b.toDouble()
    val c : Char = b.toChar()
}

/* 연산자 */
fun opTest() {
    val x1 = 5 / 2 // Int
    val x2 = 5L / 2 // Long
    val x3 = 5 / 2.toDouble() // Double

    //bitwise operation, Int와 Long만 가능
    /*
    shl(bits) – signed shift left
    shr(bits) – signed shift right
    ushr(bits) – unsigned shift right
    and(bits) – bitwise and
    or(bits) – bitwise or
    xor(bits) – bitwise xor
    inv() – bitwise inversion
     */

    //NaN은 자기자신과 같음
    //NaN은 POSITIVE_INFINITY을 포함하여 어떤수보다 큼
    //-0.0은 0.0보다 작음
}

/* Character */
fun chTest() {
    // Character은 쉼표를 사용하여 표현함
    val c = '0'
    //백슬래시를 사용한 Character은 다음과 같음
    //\t, \b, \n, \r, \', \", \\ , \$, unicode 형식
}

/* Boolean */
fun boolTest() {
    val a = true
    val b = false
    //or
    println(a || b) // true
    //and
    println(a && b) // false
    //not
    println(!a) // false
}

/* Array */
fun arrayTest() {
    // Array<Int> with [1,2,3]
    val a = arrayOf(1,2,3)

    // Array<Int> with [null,null,null]
    val b = arrayOfNulls<Int>(3)

    //Array<String> with ["0", "1", "4", "9", "16"] using lambda
    val asc = Array(5) { i -> (i * i).toString() }

    //원시타입 Array는 다음과 같이 생성 가능함
    // Array<Int>> with [0, 0, 0, 0, 0]
    val intArr1 = IntArray(5)

    // Array<Int> with [42, 42, 42, 42, 42]
    val intArr2 = IntArray(5) { 42 }

    // Array<Int> with [0, 1, 2, 3, 4] using lambda
    var intArr3 = IntArray(5) { it * 1 }
}

/* 문자열 */
fun stringTest() {
    //문자열의 각 글자는 s[i]로 접근가능함
    val s1 = "abcd"
    for (c in s1) { println(c) }

    //+ 연산자로 문자열 합치기 가능
    val s2 = "abc"+1
    println(s2+"def")

    //공백은 trimMargin()으로 제거할 수 있음. 기본값으로 | 을 기준으로 앞의 공백을 없애며,
    //trimMargin(">")와 같이 공백을 없앨 기준 문자열을 선택할 수 있음
    val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()
    println(text)

    //String template
    //문자열 내에서는 $ 기호를 통해 변수 출력, $기호를 출력하고 싶은경우 \$를 사용함
    val s = "1234"
    println("this is ${s}\$") // this is 1234$
}

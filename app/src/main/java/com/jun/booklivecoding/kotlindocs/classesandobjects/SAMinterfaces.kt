package com.jun.booklivecoding.kotlindocs.classesandobjects

/*
하나의 추상메소드만 가진 인터페이스를 기능적 인터페이스(functional interface) 또는 단일 추상메소드(SAM) 인터페이스라고 함.
여러개의 비추상적 멤버를 가질수 있지만 단 하나의 추상멤버를 가져야 함
코틀린에서는 기능적 인터페이스를 선언할 때, fun 수정자를 사용함
 */
fun interface KRunnable {
    fun invoke()
}

/*
기능적 인터페이스에서, 람다식을 사용하여 코드를 더 간결하고 읽기 쉽게 만드는 SAM 변환을 사용할 수 있음
기능 인터페이스를 구현한 클래스를 만드는것 대신에, 람다식을 사용할 수 있음
SAM 변환에 따라, 코틀린은 기호가 인터페이스의 단일 메서드 기호와 일치하는 모든 람다 표현식을 동일한 인터페이스를 구현하는 클래스의 인스턴스로 변환 할 수 있음
 */
fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

/*
SAM을 사용하지 않으면 다음과 같이 표현됨
 */
val isEven1 = object : IntPredicate {
    override fun accept(i: Int): Boolean {
        return i % 2 == 0
    }
}

/*
SAM을 사용하면 다음과 같이 동일한 코드로 표현됨
 */
val isEven2 = IntPredicate { it % 2 == 0 }

/*
기능 인터페이스 vs 타입 앨리어스
기능 인터페이스와 타입 앨리어스는 다른목적으로 사용함

타입 앨리어스는 기존 유형의 이름일뿐 이고, 새로운 타입을 생성하지 않음. 기능 인터페이스는 새 유형을 생성함
타입 앨리어스에는 멤버가 하나만 있을 수 있지만 기능 인터페이스에는 여러 비추상 멤버와 하나의 추상 멤버가 있을 수 있음
기능적 인터페이스는 다른 인터페이스를 구현하고 확장 할수도 있음
위의 사항을 고려하면 기능 인터페이스가 더 유연하고 타입 앨리어스보다 더 많은 기능을 제공합니다.

 */


//https://kotlinlang.org/docs/reference/fun-interfaces.html
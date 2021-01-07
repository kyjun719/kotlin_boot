package com.jun.booklivecoding.kotlindocs.classesandobjects

/*
sealed 클래스는 값이 제한된 집합의 유형 중 하나를 가질 수 있지만 다른 유형은 가질 수 없는 경우 제한된 클래스 계층을 나타내는 데 사용됨
enum 클래스의 확장이라고 볼수도 있음
enum상수와 sealed 클래스 둘 다 유형을 enum값 집합으로 제한할 수 있음.
하지만 각 enum 상수는 단일 인스턴스로만 존재할 수 있지만 sealed 클래스의 하위 클래스는 상태를 가질 수 있는 인스턴스를 여러개 가질 수 있음
sealed 클래스를 선언하기 위해서는, 클래스명 앞에 sealed 접근자를 붙여야함
sealed 클래스는 하위 클래스를 가질 수 있지만, 하위클래스는 sealed 클래스가 선언되어있는 파일에서만 선언할 수 있음
1.1 이전버전에서는 sealed 클래스 내부에 선언해야함
 */

sealed class Expr
//1.1부터 data 클래스도 다른 클래스를 확장할 수 있음
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

/*
sealed 클래스는 그 자체로 추상화(abstract) 되어있으며, 직접 초기화 할 수 없고 추상 멤버를 가질 수 있음
sealed 클래스는 private가 아닌 생성자를 허용하지 않음(기본적으로 private 생성자가 있음)
sealed 클래스를 간접적으로 확장하는 하위 클래스는 같은파일이 아닌 다른곳에 선언되어도 됨
 */

/*
sealed 클래스를 사용함으로써 주요한 장점은 when 표현식에서 사용할 때 나타남
조건문이 모든 경우에 해당하는지 검증하는것이 가능하면, else를 사용하지 않아도 됨
하지만 when을 표현식(결과를 사용함)으로 사용하는 경우에만 동작함
 */

//https://kotlinlang.org/docs/reference/sealed-classes.html
//https://kychul98.tistory.com/92
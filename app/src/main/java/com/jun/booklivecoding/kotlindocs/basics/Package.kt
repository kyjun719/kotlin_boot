package com.jun.booklivecoding.kotlindocs.basics

/* 패키지 */
//기본적으로 포함되는 패키지는 다음과 같음
/*
kotlin.*
kotlin.annotation.*
kotlin.collections.*
kotlin.comparisons.* (since 1.1)
kotlin.io.*
kotlin.ranges.*
kotlin.sequences.*
kotlin.text.*
 */

/*
타겟 플랫폼에 따라 추가로 포함되는 패키지
JVM:
    java.lang.*
    kotlin.jvm.*
JS:
    kotlin.js.*
 */

//다음과 같이 라이브러리를 불러옴
/*
import org.example.Message // Message 사용 가능
import org.example.* // 'org.example' 아래의 모든것 사용 가능
//as 키워드로 라이브러리의 이름 재정의 가능
import org.test.Message as testMessage // 'org.test.Message'를 testMessage로 접근
 */

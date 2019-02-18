package com.example.demo

fun doSomething(str: String, f: (String) -> String, index: Int = 0): String {
    return f(str)
}

//fun main(args: Array<String>) {
//    println(doSomething("123", { s -> s + s }))
//}
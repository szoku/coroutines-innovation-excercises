package fundamentals // ktlint-disable filename

import kotlin.concurrent.thread

// a) Creating a thread in Kotlin is similar to doing so in Java.
// b) We can use the function thread() that Kotlin provides
fun main() {
    println("main starts")
    threadRoutine(1, 500)
    threadRoutine(2, 300)
    Thread.sleep(1000)
    println("main ends")
}

fun threadRoutine(number: Int, delay: Long) {
    thread {
        println("Thread $number starts work on ${Thread.currentThread().name}")
        Thread.sleep(delay)
        println("Thread $number has finished work on ${Thread.currentThread().name}")
    }
}

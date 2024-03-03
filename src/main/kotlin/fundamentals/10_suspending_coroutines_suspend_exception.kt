package fundamentals // ktlint-disable filename

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

// Suspending functions are at the center of everything coroutines. A suspending function is simply a function that can be paused and resumed at a later time. They can execute a long running operation and wait for it to complete without blocking.
// a) Add The suspend keyword which means that function can be blocking.
// b) Enclose runBlocking invocation.
// c) Using example from previous exercise, expand it with a call to
// repeat(8) {
//    println("other tasks is working on ${Thread.currentThread().name}")
//    delay(100)
// }

class MyException : Throwable("Just an exception")

fun main() = runBlocking {
    println("main starts")
    joinAll(
        async { suspendingCoroutineWithException() },
        async { suspendingCoroutineWithException() },
        async {
            repeat(8) {
                println("other tasks is working on ${Thread.currentThread().name}")
                delay(100)
            }
        },
    )
    println("main ends")
}

suspend fun suspendingCoroutineWithException() {
    try {
        suspendCoroutine<Unit> { continuation ->
            continuation.resumeWithException(MyException())
        }
    } catch (e: MyException) {
        println("Caught!")
    }
}

package fundamentals // ktlint-disable filename

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

// Suspending functions are at the center of everything coroutines. A suspending function is simply a function that can be paused and resumed at a later time. They can execute a long running operation and wait for it to complete without blocking.
// a) Using example from previous exercise, expand it with a call to
// repeat(8) {
//    println("other tasks is working on ${Thread.currentThread().name}")
//    delay(100)
// }

fun main() = runBlocking {
    println("main starts")
    joinAll(
        async { parallelCoroutine(1, 500) },
        async { parallelCoroutine(2, 300) },
        async {
            repeat(8) {
                println("other tasks is working on ${Thread.currentThread().name}")
                delay(100)
            }
        },
    )
    println("main ends")
}

suspend fun parallelCoroutine(number: Int, delay: Long) {
    println("Coroutine $number starts work on ${Thread.currentThread().name}")
    delay(delay)
    println("Coroutine $number has finished on ${Thread.currentThread().name}")
}

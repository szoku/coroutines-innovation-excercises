package fundamentals // ktlint-disable filename

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

// Suspending functions are at the center of everything coroutines. A suspending function is simply a function that can be paused and resumed at a later time. They can execute a long running operation and wait for it to complete without blocking.
// a) Add The suspend keyword which means that function can be blocking.
// b) Enclose runBlocking invocation.
// c) Using example from previous exercise, expand it with a call to
// repeat(8) {
//    println("other tasks is working on ${Thread.currentThread().name}")
//    delay(100)
// }

private val executor = Executors.newSingleThreadScheduledExecutor {
    Thread(it, "scheduler").apply { isDaemon = true }
}

fun main() = runBlocking {
    println("main starts")
    joinAll(
        async { suspendingCoroutine(1, 500) },
        async { suspendingCoroutine(2, 300) },
        async {
            repeat(8) {
                println("other tasks is working on ${Thread.currentThread().name}")
                delay(100)
            }
        },
    )
    println("main ends")
}

suspend fun suspendingCoroutine(number: Int, delay: Long) {
    suspendCoroutine { continuation ->
        executor.schedule({
            println("Coroutine $number starts work on ${Thread.currentThread().name}")
            continuation.resume(Unit)
            println("Coroutine $number has finished on ${Thread.currentThread().name}")
        }, delay, TimeUnit.MILLISECONDS)
    }
}

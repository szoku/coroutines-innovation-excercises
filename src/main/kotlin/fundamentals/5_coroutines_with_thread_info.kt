package fundamentals // ktlint-disable filename

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

// Creating too many threads can actually make an application underperform in some situations
// threads are objects which impose overhead during object allocation and garbage collection.

// To overcome these issues, Kotlin introduced a new way of writing asynchronous, non-blocking code; the Coroutine.
//  Similar to threads, coroutines can run in concurrently, wait for, and communicate with each other with the difference that creating them is way cheaper than threads.

// a) Add The suspend keyword and runBlocking blocking call.
// b) Call threadInfoCoroutine function with an async
fun main() = runBlocking {
    println("main starts")
    joinAll(
        async { threadInfoCoroutine(1, 500) },
        async { threadInfoCoroutine(2, 300) },
    )
    println("main ends")
}

suspend fun threadInfoCoroutine(number: Int, delay: Long) {
    println("Coroutine $number starts work on ${Thread.currentThread().name}")
    delay(delay)
    println("Coroutine $number has finished on ${Thread.currentThread().name}")
}

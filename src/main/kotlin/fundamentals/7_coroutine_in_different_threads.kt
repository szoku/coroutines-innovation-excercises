package fundamentals // ktlint-disable filename

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

// in order not to block main thread we may switch context using withContext() function in Kotlin
// it is used to specify a coroutine context for a specific block of code. It allows you to switch to a different coroutine context temporarily within a coroutine
//
// a) inside threadSwitchingCoroutine() add withContext(Dispatchers.Default) function and print name of it's Thread
fun main() = runBlocking {
    println("main starts")
    joinAll(
        async { threadSwitchingCoroutine(1, 500) },
        async { threadSwitchingCoroutine(2, 300) },
    )
    println("main ends")
}

suspend fun threadSwitchingCoroutine(number: Int, delay: Long) {
    println("Coroutine $number starts work on ${Thread.currentThread().name}")
    delay(delay)
    withContext(Dispatchers.Default) {
        println("Coroutine $number has finished on ${Thread.currentThread().name}")
    }
}

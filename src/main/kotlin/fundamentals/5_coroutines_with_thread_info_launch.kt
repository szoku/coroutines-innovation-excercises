package fundamentals // ktlint-disable filename

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// The launch function is a coroutine builder that starts a new coroutine without blocking the current thread

// a) using launch call a threadLaunchCoroutine which is not suspend function
// b) using join() on a job, make the program awaits until coroutine will finish
fun main() = runBlocking {
    println("main starts")
    val job = launch { threadLaunchCoroutine(1) }
    job.join()
    println("main ends")
}

fun threadLaunchCoroutine(number: Int) {
    println("Thread with $number and a name ${Thread.currentThread().name} has run.")
}

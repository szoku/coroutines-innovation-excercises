package fundamentals // ktlint-disable filename

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

// Let’s create the first coroutine using 1 example as a reference.
// a) Add The suspend keyword which means that function can be blocking.
// b) Suspend functions are only allowed to be called from a coroutine or another suspend function. Therefore, enclose it runBlocking invocation.
// c) Call coroutineLogic function with an async
// d) You want to call coroutineLogic multiple times in parallel, and wait for all of them to finish. In order to achieve that use joinAll
fun main() = runBlocking {
    println("main starts")
    joinAll(
        async { coroutineLogic(1, 500) },
        async { coroutineLogic(2, 300) },
    )
    println("main ends")
}

suspend fun coroutineLogic(number: Int, delay: Long) {
    println("Coroutine $number starts work")
    delay(delay)
    println("Coroutine $number has finished")
}

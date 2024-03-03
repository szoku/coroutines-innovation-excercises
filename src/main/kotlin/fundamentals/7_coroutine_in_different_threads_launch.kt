package fundamentals // ktlint-disable filename

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// a) modify launch function to a launch(Dispatchers.Default)
// b) use join() funciton in order to wait for the corutine
fun main() = runBlocking {
    println("main starts")
    val job = launch(Dispatchers.Default) { threadLaunchCoroutine(1) }
    job.join()
    println("main ends")
}

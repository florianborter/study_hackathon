package ch.bfh.kotlin.experiments.kotlin2.exercises.async

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

data class PizzaOrder(val orderNumber: Int, val waiter: String = "Default")
val counter = AtomicInteger()

/*fun CoroutineScope.takeOrder(name: String): ReceiveChannel<PizzaOrder> = produce{
    send(PizzaOrder(counter.incrementAndGet(), name))
}*/

suspend fun takeOrder(name: String, channel: Channel<PizzaOrder>) {
    while (true) {
        println("Thread for waiter $name: " + Thread.currentThread().id)
        channel.send(PizzaOrder(counter.incrementAndGet(), name))
        delay(Random.nextLong(1000))
    }
}

suspend fun producePizza(name: String, channel: Channel<PizzaOrder>) {
    while (true) {
        println("Thread for producer $name: " + Thread.currentThread().id)
        val order = channel.receive()
        println("Producer $name is producing Pizza ${order.orderNumber}, order taken by ${order.waiter}")
        delay(250)
    }
}

fun main() {
    runBlocking {
        val job = launch(Dispatchers.Default) {
            val channel = Channel<PizzaOrder>(50)
            launch {
                producePizza("Mario", channel)
            }
            launch {
                takeOrder("Luca", channel)
            }
            launch {
                takeOrder("Francesco", channel)
            }
            launch {
                takeOrder("Enrico", channel)
            }
            launch {
                producePizza("Luigi", channel)
            }
        }
        delay(20000)
        println("stopping all")
        job.cancelAndJoin()
    }

}
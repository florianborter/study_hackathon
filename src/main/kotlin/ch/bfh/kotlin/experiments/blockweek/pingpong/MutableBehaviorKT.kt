/**
 * Block Week 2 - Ping-Pong Akka example.
 */
package ch.bfh.kotlin.experiments.blockweek.pingpong

import akka.actor.typed.Behavior
import akka.actor.typed.Signal
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Receive

/**
 * Adaptor class based on
 * <a href="https://www.linkedin.com/pulse/kotlin-akka-typed-pawel-wlodarski" target="_blank">Pawel Wlodarski</a>'s
 * idea.
 */
abstract class MutableBehaviorKT<T>(context: ActorContext<T>?) : AbstractBehavior<T>(context) {
    abstract fun onMessage(msg: T): Behavior<T>

    override fun createReceive(): Receive<T> = object : Receive<T>() {
        override fun receiveSignal(msg: Signal?): Behavior<T> = this@MutableBehaviorKT
        override fun receiveMessage(msg: T): Behavior<T> {
            return onMessage(msg)
        }
    }
}

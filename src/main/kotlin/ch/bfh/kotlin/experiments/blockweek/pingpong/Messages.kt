package ch.bfh.kotlin.experiments.blockweek.pingpong

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior




enum class Messages: Command {
    INIT, PING, PONG
}

class InitMessage(replyTo: ActorRef<Command>) : Command {
    val replyTo: ActorRef<Command>

    init {
        this.replyTo = replyTo
    }
}

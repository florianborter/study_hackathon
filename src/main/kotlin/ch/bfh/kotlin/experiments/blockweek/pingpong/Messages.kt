package ch.bfh.kotlin.experiments.blockweek.pingpong

import akka.actor.typed.ActorRef

enum class Messages: Command {
    INIT, PING, PONG
}

class Messages: Command {
    val message: ActorRef
}
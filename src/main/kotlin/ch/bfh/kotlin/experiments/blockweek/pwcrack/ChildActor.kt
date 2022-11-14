package ch.bfh.kotlin.experiments.blockweek.pwcrack

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive
import java.io.BufferedReader
import java.io.FileReader

const val PASSWORDS_FILENAME = "/blockweek/xato-net-10-million-passwords.txt"
const val HASHES_FILENAME = "/blockweek/out.txt"

class ChildActor(context: ActorContext<Message>): AbstractBehavior<Message>(context) {
    var offset = 0
    var size = 0
    lateinit var rootActorRef: ActorRef<Message>

    fun setup(message: Message): ChildActor {
        return this
    }

    override fun createReceive(): Receive<Message> {
        return newReceiveBuilder()
            .onMessage(StartCrackMessage::class.java, this::crack)
            .build()
    }

    private fun crack(message: StartCrackMessage): Behavior<Message> {
        println("Hello I'm crackin'")
        return this
    }

    private fun readHashes(offset: Int, size: Int) {
        BufferedReader(FileReader(ChildActor::class.java.getResource(HASHES_FILENAME)?.path ?: "" )).use {

        }

    }

    companion object {
        fun create(): Behavior<Message> {
            return Behaviors.setup{ ChildActor(it) }
        }
    }
}

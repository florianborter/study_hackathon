/**
 * Block Week 2 - Ping-Pong Akka example.
 */
package ch.bfh.kotlin.experiments.blockweek.pwcrack

import akka.actor.typed.Behavior
import akka.actor.typed.Props
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive
import java.io.FileReader

class RootActor(context: ActorContext<Message>) : AbstractBehavior<Message>(context) {
    override fun createReceive(): Receive<Message> {
        return newReceiveBuilder()
            .onMessage(InitCrackMessage::class.java, this::initCracking)
            .onMessage(ReportCrackSuccess::class.java, this::crackSuccess)
            .build()
    }

    private fun initCracking(message: InitCrackMessage): Behavior<Message> {
        val child = ChildActor.create()

        val inFile = FileReader(ChildActor::class.java.getResource(HASHES_FILENAME)?.path ?: "")
        val numberOfLines = inFile.readLines().size

        val actorNumber = 8
        val size = numberOfLines/actorNumber

        for (i in 0 until actorNumber) {
            val childActor = context.spawn(child, "Child$i", Props.empty())
            if (i == actorNumber -1) {
                childActor.tell(StartCrackMessage(i * size, numberOfLines - (i * size), context.self))
            } else {
                childActor.tell(StartCrackMessage(i * size, size, context.self))
            }
        }

        return this
    }

    private fun crackSuccess(message: ReportCrackSuccess): Behavior<Message> {
        println("Found username: ${message.username} with password: ${message.password}")
        return this
    }


    companion object {
        /**
         * Factory method for creating an instance of this actor.
         *
         * @return this actor
         */
        @JvmStatic
        fun create(): Behavior<Message> {
            return Behaviors.setup { RootActor(it) }
        }
    }

}

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

class RootActor(context: ActorContext<Message>): AbstractBehavior<Message>(context) {
    override fun createReceive(): Receive<Message> {
        return newReceiveBuilder().onMessage(InitCrackMessage::class.java, this::initCracking).build()
    }

    private fun initCracking(message: InitCrackMessage): Behavior<Message> {
        val child = ChildActor.create()
        val childActor = context.spawn(child, "Child", Props.empty())
        childActor.tell(StartCrackMessage(0, 100, context.self))
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
            return Behaviors.setup{ RootActor(it) }
        }
    }

}

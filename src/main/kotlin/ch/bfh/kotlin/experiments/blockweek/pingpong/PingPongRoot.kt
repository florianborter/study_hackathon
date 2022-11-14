/**
 * Block Week 2 - Ping-Pong Akka example.
 */
package ch.bfh.kotlin.experiments.blockweek.pingpong

import akka.actor.typed.Behavior
import akka.actor.typed.Props
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive

//import ch.bfh.kotlin.experiments.blockweek.pingpong.PingPongRoot.InitMessage

/**
 * The root actor of the Akka ping-pong system. It spawns ping and pong child
 * actors and sets up the mutual references such that, as a next step, the
 * children can exchange messages.
 */
class PingPongRoot(context: ActorContext<Command>): MutableBehaviorKT<Command>(context)
/**
 * Private constructor -- use static factory method.
 *
 * @param context the actor's context
 */
{

    /**
     * Handles the init message sent from the main program.
     *
     * @return a (typed) reference to this actor
     */

    override fun createReceive(): Receive<Command> {
        return newReceiveBuilder().onMessageEquals(Messages.INIT, this::initPingAndPong).build()
    }

    override fun onMessage(msg: Command): Behavior<Command> {
        TODO("Not yet implemented")
    }

    /**
     * Creates ping and pong child actors and initializes them with mutual
     * references. And at the end, it tell ping to start.
     *
     * @param message the init message
     * @return this actor
     */
    fun initPingAndPong(): Behavior<Command> {
        val ping = Ping.create()
        val pong = Pong.create()
        println(Messages.INIT)
        val pingActor = context.system.systemActorOf(ping, "Ping", Props.empty())
        val pongActor = context.system.systemActorOf(pong, "Pong", Props.empty())
        pingActor.tell(InitMessage(pongActor))
        pongActor.tell(InitMessage(pingActor))
        pingActor.tell(Messages.PONG)
        return this
    }


    companion object {
        /**
         * Factory method for creating an instance of this actor.
         *
         * @return this actor
         */
        @JvmStatic
        fun setup(): Behavior<Command> {
            return Behaviors.setup{ PingPongRoot(it) }
        }
    }

}

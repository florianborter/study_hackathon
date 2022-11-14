/**
 * Block Week 2 - Ping-Pong Akka example.
 */
package ch.bfh.kotlin.experiments.blockweek.pingpong

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive

//import ch.bfh.kotlin.experiments.blockweek.pingpong.Pong.Message

/**
 * A simple actor having the 'pong' role. It receives the initialization message
 * containing the reference to ping. After having received the pong, it sends a
 * ping to ping, and awaits the answer of ping. Having received the answer of
 * ping, this repeats indefinitely.
 *
 */
class Pong(context: ActorContext<Command>): MutableBehaviorKT<Command>(context)
/**
 * Private constructor.
 *
 * @param context the context
 */
{
    /**
     * The amount (in milliseconds) to sleep between the sending of messages.
     */
    val sleepTime = 2 * 1000

    /**
     * The reference to ping we remember, once received from PingPongRoot.
     */


    /**
     * The common abstraction of all messages for this actor.
     */


    /**
     * The setup message containing the reference to pong.
     */


    /**
     * The pong message being sent by ping.
     */


    /**
     * Handles 'SetupMessage' and 'PongMessage' messages.
     *
     * @return an instance of this actor
     */
    fun setup() {
        TODO("implement")
    }


    /**
     * Handles the setup message
     *
     * @param setupMessage message that contains the reference to ping
     * @return ourselves
     */


//TBD: notwendig?
    override fun createReceive(): Receive<Command> {
        return newReceiveBuilder().onMessageEquals(Messages.PING, this::sendPong).build()
    }



    //DAFUQQQ
    override fun onMessage(msg: Command): Behavior<Command> {
        TODO("Not yet implemented")
    }

    /**
     * Sends the pong message to ping.
     *
     * @return ourselves
     */
    private fun sendPong(): Behavior<Command> {
        //TODO: Send PING
        println("blub")
        return this
    }


    companion object {
        /**
         * Factory method for creating an instance of this actor.
         *
         * @return this actor
         */
        @JvmStatic
        fun create(): Behavior<Command> {
            return Behaviors.setup{ Pong(it) }
        }
    }
}

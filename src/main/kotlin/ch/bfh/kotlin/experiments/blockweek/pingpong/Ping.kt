/**
 * Block Week 2 - Ping-Pong Akka example.
 */
package ch.bfh.kotlin.experiments.blockweek.pingpong

import akka.actor.Actor
import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive

/**
 * A simple actor having the 'ping' role. It receives the initialization message
 * containing the reference to pong. After having received the start message, it
 * sends a pong to pong, and awaits the answer of pong. Having received the
 * answer of pong, this repeats indefinitely.
 */
class Ping(context: ActorContext<Command>): MutableBehaviorKT<Command>(context)
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
     * The reference to pong we remember, once received from PingPongRoot
     */
    lateinit var pong: ActorRef<Command>


    /**
     * The common abstraction of all messages for this actor.
     */
    //in separate File

    /**
     * The setup message containing the reference to pong.
     */


    /**
     * The start message telling ping to start sending a message to pong.
     */
    fun start() {
        TODO("implement")
    }


    /**
     * The ping message being sent by pong.
     */


    /**
     * Handles the 'SetupMessage', 'StartMessage',
     * and 'PingMessage' messages.
     *
     * @return an instance of this actor
     */


    /**
     * Handles the setup message
     *
     * @param setupMessage message that contains the reference to pong
     * @return ourselves
     */
    fun setup(setupMessage: ActorRef<Command>): Ping {
        pong = setupMessage
        return this
    }


    /**
     * Starts the sending of pong messages to pong.
     *
     * @return ourselves
     */


    /**
     * Receives the ping message of pong and calls 'extracted()'.
     *
     * @return ourselves
     */
    override fun createReceive(): Receive<Command> {
        return newReceiveBuilder().onMessageEquals(Messages.PONG, this::sendPing).build()
    }

    /**
     * Sends the pong message to pong.
     *
     * @return ourselves
     */
    private fun sendPing(): Behavior<Command> {
        //TODO: Send PING
        println("gugus")
        return this
    }

    //DAFUQQQ
    override fun onMessage(msg: Command): Behavior<Command> {
        TODO("Not yet implemented")
    }


    companion object {
        /**
         * Factory method for creating an instance of this actor.
         *
         * @return this actor
         */
		@JvmStatic
        fun create(): Behavior<Command> {
            return Behaviors.setup{ Ping(it) }
        }
    }


}

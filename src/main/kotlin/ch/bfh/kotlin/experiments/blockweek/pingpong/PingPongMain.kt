/**
 * Block Week 2 - Ping-Pong Akka example.
 */
package ch.bfh.kotlin.experiments.blockweek.pingpong

//import ch.bfh.kotlin.experiments.blockweek.pingpong.PingPongRoot.InitMessage
import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.Props
import akka.actor.typed.javadsl.Behaviors

/**
 * This is the main class of a simple Akka ping pong application in Kotlin. A root actor
 * PingPongMain is created which, upon receiving an init message, creates the
 * two actors 'ping' and 'pong' via the 'aContext.spawn()' methods. The two
 * actors are then configured with the references of the other actor (i.e., pong
 * gets the reference of ping, and ping gets the reference of pong). Finally,
 * ping is sent the start message, and from now on ping and pong exchange
 * messages. Hit the 'ENTER' key to terminate the Akka application.
 */
object PingPongMain {
    /**
     * Main entry point of the application. Hit the 'ENTER' key to terminate
     * the Akka application.
     *
     * @param args not used
     * @throws InterruptedException may be thrown if main thread is interrupted
     * unexpectedly
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val system = ActorSystem.create(PingPongRoot.setup(), "PingPongSystem")

        system.tell(Messages.INIT)
        system.tell(Messages.PING)
        system.tell(Messages.PONG)

        system.terminate()
    }
}

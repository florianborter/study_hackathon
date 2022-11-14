package ch.bfh.kotlin.experiments.blockweek.pwcrack

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive
import java.io.FileReader

const val PASSWORDS_FILENAME = "/blockweek/xato-net-10-million-passwords.txt"
const val HASHES_FILENAME = "/blockweek/out.txt"

class ChildActor(context: ActorContext<Message>) : AbstractBehavior<Message>(context) {
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
        val myHashes = readHashes(message.offset, message.size)
        val passwords = readPasswords()

        for (pw in passwords) {
            for (hash in myHashes) {
                val hashedPw = DecryptPasswords.hash("sha-512", pw + hash.salt)
                if (hash.hash == hashedPw) {
                    rootActorRef.tell(ReportCrackSuccess(username = hash.user, password = pw))
                }
            }
        }

        return this
    }

    private fun readHashes(offset: Int, size: Int): List<HashEntry> {
        val inFile = FileReader(ChildActor::class.java.getResource(HASHES_FILENAME)?.path ?: "")
        val lines = inFile.readLines()
        val effectiveLines = lines.subList(offset, offset + size)
        return effectiveLines.map {
            val splitLine = it.split(" ")
            HashEntry(splitLine[0], splitLine[1], splitLine[2])
        }
    }

    private fun readPasswords(): List<String> {
        val inFile = FileReader(ChildActor::class.java.getResource(PASSWORDS_FILENAME)?.path ?: "")
        return inFile.readLines()
    }


    companion object {
        fun create(): Behavior<Message> {
            return Behaviors.setup { ChildActor(it) }
        }
    }
}

class HashEntry(val user: String, val salt: String, val hash: String)
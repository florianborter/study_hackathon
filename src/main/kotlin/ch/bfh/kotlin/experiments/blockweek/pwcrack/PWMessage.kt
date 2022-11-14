package ch.bfh.kotlin.experiments.blockweek.pwcrack

import akka.actor.typed.ActorRef

interface Message

class StartCrackMessage(val offset: Int, val size: Int, val ref: ActorRef<Message>) : Message

class InitCrackMessage : Message

class ReportCrackSuccess(val username: String, val password: String): Message
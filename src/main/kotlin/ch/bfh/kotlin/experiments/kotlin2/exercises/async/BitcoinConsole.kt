package ch.bfh.kotlin.experiments.kotlin2.exercises.async

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL

const val url = "https://api.coindesk.com/v1/bpi/currentprice.json"

data class TimeStamps(
    val updated: String = "",
    val updatedISO: String = "",
    val updateduk: String = ""
)

data class Currency(
    val code: String = "",
    val symbol: String = "",
    val rate: String = "",
    val description: String = "",
    val rate_float: Float = 0.0F
)

data class Bpi(
    val USD: Currency,
    val GBP: Currency,
    val EUR: Currency
)

data class BitCoinData(
    val time: TimeStamps,
    val disclaimer: String,
    val chartName: String,
    val bpi: Bpi
) {
    override fun toString(): String {
        return "Rates on ${time.updated}: ${bpi.EUR.rate_float} ${bpi.EUR.code}" +
                ", ${bpi.GBP.rate_float} ${bpi.GBP.code}, ${bpi.USD.rate_float} ${bpi.USD.code}"
    }
}

fun main() {
    val mapper = jacksonObjectMapper()
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    runBlocking {
        val channel = Channel<BitCoinData>(CONFLATED)
        launch(Dispatchers.Default) {
            while (true) {
                println("Thread in Coroutine 1: " + Thread.currentThread().id)
                val json = URL(url).readText()
                val bitcoinData = mapper.readValue(json, BitCoinData::class.java)
                channel.send(bitcoinData)
                delay(10000)
            }
        }
        launch(Dispatchers.Default) {
            while (true) {
                println("Thread in Coroutine 2: " + Thread.currentThread().id)
                val bitcoinData = channel.receive()
                println("received BitCoinData: $bitcoinData")
            }
        }
    }
}
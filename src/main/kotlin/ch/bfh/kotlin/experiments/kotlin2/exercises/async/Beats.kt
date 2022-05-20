package ch.bfh.kotlin.experiments.kotlin2.exercises

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import javax.sound.sampled.AudioSystem

fun playSound(file: String) {
    println("play $file")
    val clip = AudioSystem.getClip()
    val audioInputStream = AudioSystem.getAudioInputStream(File(file))
    clip.open(audioInputStream)
    clip.start()
}

suspend fun playBeat(beat: Pair<String, String>) {
    for (index in beat.first.indices) {
        if (beat.first[index] == 'x') {
            runBlocking {
                launch(Dispatchers.Default) {
                    playSound(beat.second)
                }
            }
        }
        delay(100)
    }
}

fun playBeats(beats: List<Pair<String, String>>) {
    runBlocking {
        for (beat in beats) {
            launch(Dispatchers.Default) {
                playBeat(beat)
            }
        }
    }
}

fun main() {
    val dir = "src/main/resources/audio/"
    val beatPairs = mutableListOf<Pair<String, String>>()
    beatPairs.add(Pair("x-x-x-x-x-x-x-x-x-", dir + "toms.aiff"))
    beatPairs.add(Pair("x-----x-----x-----", dir + "crash_cymbal.aiff"))

    playBeats(beatPairs)

    Thread.sleep(1000)
}
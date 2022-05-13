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

suspend fun playBeat(beat: String, beatFile: String) {
    for (index in beat.indices) {
        if (beat.get(index) == 'x') {
            runBlocking {
                launch(Dispatchers.Default) {
                    playSound(beatFile)
                }
            }
        }
        delay(100)
    }
}

fun playBeats(beats: List<String>, beatFiles: List<String>) {
    runBlocking {
        for (index in beats.indices) {
            launch(Dispatchers.Default) {
                playBeat(beats[index], beatFiles[index])
            }
        }
    }
}

fun main() {
    val dir = "src/main/resources/audio/"
    val beats = mutableListOf<String>()
    val beatFiles = mutableListOf<String>()
    beats.add("x-x-x-x-x-x-x-x-x-")
    beatFiles.add(dir + "toms.aiff")

    beats.add("x-----x-----x-----")
    beatFiles.add(dir + "crash_cymbal.aiff")

    playBeats(beats, beatFiles)

    Thread.sleep(1000)
}
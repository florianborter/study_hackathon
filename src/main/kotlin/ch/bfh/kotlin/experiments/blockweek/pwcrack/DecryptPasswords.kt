package ch.bfh.kotlin.experiments.blockweek.pwcrack

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

object DecryptPasswords {
    /**
     * Return a string representing the hash of the given textToHash, using
     * the algorithm algorithm. The hash is returned encoded in Base64
     * using UTF-8
     *
     * @param  algorithm for the hashing of the string
     * @param  textToHash the text that is to be hashed
     * @return      The base64 encoding of the hash code
     */
    @Throws(NoSuchAlgorithmException::class)
    fun hash(algorithm: String?, textToHash: String): String {
        val digest = MessageDigest.getInstance(algorithm)
        val byteOfTextToHash = textToHash.toByteArray(StandardCharsets.UTF_8)
        val hashedByetArray = digest.digest(byteOfTextToHash)
        return Base64.getEncoder().encodeToString(hashedByetArray)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val start = System.currentTimeMillis()
        System.err.println("Reads a file containing  hash of passwords")
        if (args.size != 2) {
            System.err.println("Program requires two file names as argument (hashed passwords and possible passwords)")
            return
        }
        // We read the first file containing the hashed passwords
        val lines = LinkedList<String>()
        val fileName = args[0]
        System.err.println("Reading file: $fileName")
        try {
            BufferedReader(FileReader(fileName)).use { br ->
                var line = br.readLine()
                while (line != null) {
                    //System.out.println("line = "+line);
                    lines.addLast(line)
                    line = br.readLine()
                }
            }
        } catch (e1: FileNotFoundException) {
            System.err.println("File $fileName not found")
            return
        } catch (e2: IOException) {
            System.err.println("Problem reading the file $fileName")
            return
        }

        // We read the second file containing the clear text passwords
        val passwords = LinkedList<String>()
        val fileNamePasswords = args[1]
        System.err.println("Reading file: $fileNamePasswords")
        try {
            BufferedReader(FileReader(fileNamePasswords)).use { br ->
                var pwd1 = br.readLine()
                while (pwd1 != null) {
                    passwords.addLast(pwd1)
                    pwd1 = br.readLine()
                }
            }
        } catch (e1: FileNotFoundException) {
            System.err.println("File $fileNamePasswords not found")
            return
        } catch (e2: IOException) {
            System.err.println("Problem reading the file $fileNamePasswords")
            return
        }

        // For each user and for each password, we check if the
        // password corresponds to the user :
        // hash(password + salt) is equal to the hashed password.
        for (lineFile in lines) {
            val lineSplited = lineFile.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (lineSplited.size != 3) {
                System.err.println("Malformed file$fileName")
            }
            val user = lineSplited[0]
            val hashedPassword = lineSplited[2]
            val salt = lineSplited[1]
            for (pwd in passwords) {
                try {
                    val hashedPwd = hash("SHA-512", pwd + salt)
                    if (hashedPwd == hashedPassword) {
                        println("$user $pwd")
                    }
                } catch (e: NoSuchAlgorithmException) {
                    System.err.println("Algorithm SHA 512 not implmented")
                    return
                }
            }
        }

        /*
	
	*/
        val finish = System.currentTimeMillis()
        val timeElapsed = finish - start
        val seconds = timeElapsed / 1000
        val millis = timeElapsed % 1000
        System.err.println("Process duration = " + seconds + "," + millis + "s")
    }
}
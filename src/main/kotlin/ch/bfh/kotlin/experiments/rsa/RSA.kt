package ch.bfh.kotlin.experiments.rsa

import java.math.BigInteger
import kotlin.math.pow


fun main() {
    squareAndMultiply(BigInteger.valueOf(145), BigInteger.valueOf(69), BigInteger.valueOf(667))


    val p1: BigInteger = BigInteger.valueOf(9679)
    val q1: BigInteger = BigInteger.valueOf(31393)
    val e1 = 5 //this is the Public Key
    val message1 = "revealing"
    val n1: BigInteger = p1 * q1
    val phiN1 = (p1 - BigInteger.valueOf(1)) * (q1 - BigInteger.valueOf(1))
    val d1 = BigInteger.valueOf(243049421) //e * d = 1 (mod (p-1)*(q-1) ) // e * d = 1 (mod phiN )

    val encrypted1 = encrypt(e1, n1, message1)
    val decrypted1 = decrypt(d1, n1, encrypted1)
    println(decrypted1)



    val p2: BigInteger = BigInteger.valueOf(19319)
    val q2: BigInteger = BigInteger.valueOf(40153)
    val e2 = 5 //this is the Public Key
    val message2 = "5706795    37360752    33554432   11293606    37360752    15327307    82411494     9936269     3680043    5802786    72953378    52490519   37360752"
    val n2: BigInteger = p2 * q2
    val phiN2 = (p2 - BigInteger.valueOf(1)) * (q2 - BigInteger.valueOf(1))
    val d2 = BigInteger.valueOf(620525069) //e * d = 1 (mod (p-1)*(q-1) ) // e * d = 1 (mod phiN )

    val encrypted2 = encrypt(e2, n2, message2)
    val decrypted2 = decrypt(d2, n2, encrypted2)
    println(decrypted2)

}

// c = m^e mod n, Pro Buchstabe gibt die methode ein Resultat in der ArryList zurück, resultate sind c1, c2, ... , cn
fun encrypt(e: Int, n: BigInteger, message: String): List<BigInteger> {
    val encryptedMessage = mutableListOf<BigInteger>()
    for (i in message.indices) {
        val characterCode = message[i].code
        //encrypt using the formula c = m^e mod n and the square and multiply algorithm
        val c = squareAndMultiply(characterCode.toBigInteger(), e.toBigInteger(), n)
        encryptedMessage.add(c)
    }

    return encryptedMessage.toList()
}

//Decrypts the messages c1, c2, ... , cn
fun decrypt(d: BigInteger, n: BigInteger, encryptedValues: List<BigInteger>): String {
    var res = ""
    for (encryptedValue in encryptedValues) { //Loop through all encrypted values
        //decrypt using the formula c^d mod n and the square and multiply algorithm
        val decryped = squareAndMultiply(encryptedValue, d, n)
        res += Char(decryped.toInt())
    }
    return res
}

/**
 * This is the complete Square and multiply algorithm to prevent Integer-Overflow
 */
fun squareAndMultiply(base: BigInteger, exponent: BigInteger, module: BigInteger): BigInteger {
    //This calculates the powers of two existing between 2^0 and 2^29
    val powersOfTwo = mutableListOf<Pair<Int, BigInteger>>() //first = number of power, second = value
    for (i in 0 until 30) {
        powersOfTwo.add(Pair(i, 2.toDouble().pow(i.toDouble()).toBigDecimal().toBigInteger()))
    }

    //This block calculates the effective powers of two used to represent the exponent
    var tmpExp = exponent
    val effectivePowers = mutableListOf<Pair<Int, BigInteger>>()
    for (possiblePower in powersOfTwo.sortedByDescending { it.first }) {
        if (possiblePower.second <= tmpExp) {
            effectivePowers.add(possiblePower)
            tmpExp -= possiblePower.second
        }
    }

    //The heart of the algorithm, where the last value gets powered by 2 and then modulo the variable module
    val baseTimesExponentModulo =
        HashMap<Int, BigInteger>() //key is the iteration, value is the value of the calculation (power and modulo)
    var last = base
    baseTimesExponentModulo[0] = base
    for (i in 1..effectivePowers.sortedByDescending { it.first }.map { it.first }.first()) {
        val newValue = last.pow(2) % module
        last = newValue
        baseTimesExponentModulo[i] = newValue
    }

    //Select only the calculated values which are effectively used as exponents to represent the Exonent
    val effectiveValues = mutableListOf<BigInteger>()
    effectivePowers.map { it.first }
        .forEach { effectiveValues.add(baseTimesExponentModulo.getOrDefault(it, BigInteger.ZERO)) }


    //Do the last step of multiplying the results (only multiply the necessary values, used to represent the Exponent in power of two)
    var res = effectiveValues.first()
    for (i in 1 until effectiveValues.size) {
        res = (res * effectiveValues[i]) % module
    }

    return res
}

/**
 *
 *
 */
package ch.bfh.kotlin.experiments.rsa

import kotlin.math.pow

/*
Excercise A:  111416463    179103703    88614043    179103703    79460541    108344112    996051    899109    46332557
Excercise B: doesn't work, see email to Daniel Tokarev. Works with other Data
* */

fun main() {
    val p1: ULong = 9679u
    val q1: ULong = 31393u
    val e1: ULong = 5u //this is the Public Key
    val message1 = "revealing"
    val n1: ULong = p1 * q1
    val phiN1 = (p1 - 1u) * (q1 - 1u)
//    val d1: ULong = 243049421u
    val d1 = modInverse(e1, phiN1) //e * d = 1 (mod (p-1)*(q-1) ) // e * d = 1 (mod phiN )

    val encrypted1 = encrypt(e1, n1, message1)
    encrypted1.forEach { println(it) } //111416463    179103703    88614043    179103703    79460541    108344112    996051    899109    46332557
    val decrypted1 = decrypt(d1, n1, encrypted1)
    println(decrypted1)


    val p2: ULong = 19319u
    val q2: ULong = 40153u
    val e2: ULong = 5u //this is the Public Key
    val n2: ULong = p2 * q2
    val phiN2 = (p2 - 1u) * (q2 - 1u)

    val message2 = listOf<ULong>(
        5706795u,
        37360752u,
        33554432u,
        11293606u,
        37360752u,
        15327307u,
        82411494u,
        9936269u,
        3680043u,
        5802786u,
        72953378u,
        52490519u,
        37360752u
    )
//    val d2: ULong = 620525069u
    val d2 = modInverse(e2, phiN2) //e * d = 1 (mod (p-1)*(q-1) ) // e * d = 1 (mod phiN )

    val decrypted2 = decrypt(d2, n2, message2)
    println(decrypted2)

}

// c = m^e mod n, Pro Buchstabe gibt die methode ein Resultat in der ArryList zurück, resultate sind c1, c2, ... , cn
fun encrypt(e: ULong, n: ULong, message: String): List<ULong> {
    val encryptedMessage = mutableListOf<ULong>()
    for (i in message.indices) {
        val characterCode = message[i].code
        //encrypt using the formula c = m^e mod n and the square and multiply algorithm
        val c = squareAndMultiply(characterCode.toULong(), e, n)
        encryptedMessage.add(c)
    }

    return encryptedMessage.toList()
}

//Decrypts the messages c1, c2, ... , cn
fun decrypt(d: ULong, n: ULong, encryptedValues: List<ULong>): String {
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
fun squareAndMultiply(base: ULong, exponent: ULong, module: ULong): ULong {
    //This calculates the powers of two existing between 2^0 and 2^29
    val powersOfTwo = mutableListOf<Pair<Int, ULong>>() //first = number of power, second = value
    for (i in 0 until 30) {
        powersOfTwo.add(Pair(i, 2.toDouble().pow(i.toDouble()).toULong()))
    }

    //This block calculates the effective powers of two used to represent the exponent
    var tmpExp = exponent
    val effectivePowers = mutableListOf<Pair<Int, ULong>>()
    for (possiblePower in powersOfTwo.sortedByDescending { it.first }) {
        if (possiblePower.second <= tmpExp) {
            effectivePowers.add(possiblePower)
            tmpExp -= possiblePower.second
        }
    }

    //The heart of the algorithm, where the last value gets powered by 2 and then modulo the variable module
    val baseTimesExponentModulo =
        HashMap<Int, ULong>() //key is the iteration, value is the value of the calculation (power and modulo)
    var last = base
    baseTimesExponentModulo[0] = base
    for (i in 1..effectivePowers.sortedByDescending { it.first }.map { it.first }.first()) {
        val newValue = (last * last) % module
        last = newValue
        baseTimesExponentModulo[i] = newValue
    }

    //Select only the calculated values which are effectively used as exponents to represent the Exonent
    val effectiveValues = mutableListOf<ULong>()
    effectivePowers.map { it.first }
        .forEach { effectiveValues.add(baseTimesExponentModulo.getOrDefault(it, 0u)) }


    //Do the last step of multiplying the results (only multiply the necessary values, used to represent the Exponent in power of two)
    var res = effectiveValues.first()
    for (i in 1 until effectiveValues.size) {
        res = (res * effectiveValues[i]) % module
    }

    return res
}

//Calculates the modular inverse
fun modInverse(a: ULong, m: ULong): ULong {
    for (x in 1 until m.toInt()) {
        val tmp = x.toULong()
        val abc: ULong = 1u
        if (a % m * (tmp % m) % m == abc) {
            return tmp
        }
    }
    return 1u
}


/*fun crack(n: Int): Pair<Int, Int> {
    val near = sqrt(n)

}*/

/**
 * TODO: Explanation why rsa
 *
 */
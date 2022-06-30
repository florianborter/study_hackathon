package ch.bfh.kotlin.experiments.kotlin2.exercises.recursion

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

fun String.isPalindrome(): Boolean {
    val value = this.replace(Regex("[^a-zA-Z0-9]"), "")
    if (value.length <= 1) {
        return true
    }
    if (value.length == 2) {
        return value[0].equals(value[value.lastIndex], ignoreCase = true)
    } else {
        if (value[0].equals(value[value.lastIndex], ignoreCase = true)) {
            return value.substring(1 until value.lastIndex).isPalindrome()
        }
    }
    return false
}

internal class PalindromeKtTest {

    @Test
    fun isPalindrome_trivial() {
        Assertions.assertTrue("".isPalindrome())
        Assertions.assertTrue("a".isPalindrome())
        Assertions.assertTrue("aa".isPalindrome())
        Assertions.assertTrue("a a".isPalindrome())
        Assertions.assertTrue("aba".isPalindrome())
        Assertions.assertTrue("ab a".isPalindrome())
        Assertions.assertFalse("abc".isPalindrome())
        Assertions.assertTrue(" ab , a.".isPalindrome())
        Assertions.assertFalse("abc".isPalindrome())
    }

    @Test
    fun isPalindrome_DE() {
        Assertions.assertTrue("Regallager".isPalindrome())
        Assertions.assertTrue("Reittier".isPalindrome())
        Assertions.assertTrue(" Ein Neger mit Gazelle zagt im Regen nie.".isPalindrome())
        Assertions.assertTrue("Erika feuert nur untreue Fakire.".isPalindrome())
    }

    @Test
    fun isPalindrome_FR() {
        Assertions.assertTrue("Un roc cornu".isPalindrome())
        Assertions.assertTrue("  L'ami naturel ? Le rut animal.".isPalindrome())
        Assertions.assertTrue("Engage le jeu que je le gagne.".isPalindrome())
    }

}
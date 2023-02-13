package m.m.androidlab

import kotlin.random.Random

class Dice(val numSides: Int = 6) {

    fun roll() : Int {
        return 1 + Random.nextInt(numSides)
    }
}
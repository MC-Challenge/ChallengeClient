package net.challenge.client.utils

class TimeHelper {
    private var lastMS = 0.0

    fun convertToMS(d: Int): Int {
        return 1000 / d
    }

    private fun getCurrentMillis(): Long {
        return  System.nanoTime() / 1000000L
    }

    fun hasReached(milliseconds: Double): Boolean {
        return getCurrentMillis() - lastMS >= milliseconds
    }

    fun hasTimeReached(delay: Double): Boolean {
        return System.currentTimeMillis() - lastMS >= delay
    }

    val delay: Double
        get() = System.currentTimeMillis() - lastMS

    fun reset() {
        lastMS = getCurrentMillis().toDouble()
    }

    fun setLastMS() {
        lastMS = System.currentTimeMillis().toDouble()
    }

    fun setLastMS(lastMS: Double) {
        this.lastMS = lastMS
    }
}

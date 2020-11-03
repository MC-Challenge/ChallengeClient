package net.challenge.client.ui.position

/**
 * Simple position in pixels.
 */
class Position(x: Number = 0, y: Number = 0) : IPosition {

    /**
     * X-Position in pixels.
     */
    private var x: Double = x.toDouble()

    /**
     * Y-Position in pixels.
     */
    private var y: Double = y.toDouble()

    override fun getX(): Double {
        return x
    }

    override fun getY(): Double {
        return y
    }

    override fun setX(x: Number) {
        this.x = x.toDouble()
    }

    override fun setY(y: Number) {
        this.y = y.toDouble()
    }
}
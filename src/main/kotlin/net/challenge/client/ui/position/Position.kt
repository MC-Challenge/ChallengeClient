package net.challenge.client.ui.position

class Position(

        private var x: Double = 0.0,

        private var y: Double = 0.0

) : IPosition {


    override fun getAbsoluteX(): Int {
        return x.toInt()
    }

    override fun getAbsoluteY(): Int {
        return y.toInt()
    }

    override fun getRelativeX(): Double {
        return x
    }

    override fun getRelativeY(): Double {
        return y
    }

    override fun setAbsolute(x: Int, y: Int) {

    }

    override fun setRelative(x: Double, y: Double) {
    }
}
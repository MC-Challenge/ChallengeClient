package net.challenge.client.ui.widget.elements

import net.challenge.client.ui.widget.Widget
import java.awt.Color

class Rect(val color: Color) : Widget<Rect>() {

    var hoverColor: Color? = null
    var filled = true

    fun addHoverColor(c: Color): Rect {
        this.hoverColor = c
        return this
    }

    fun setFilled(b: Boolean): Rect {
        this.filled = b
        return this
    }

}
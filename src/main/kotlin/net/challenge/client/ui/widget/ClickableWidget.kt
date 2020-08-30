package net.challenge.client.ui.widget

import net.challenge.client.ui.events.input.mouse.IGuiMouseClick
import net.challenge.client.ui.widget.interaction.IClickable

/**
 * TODO Doc
 */
open class ClickableWidget<W : ClickableWidget<W>> : Widget<W>(), IClickable<W>, IGuiMouseClick {

    override val clickListeners: MutableCollection<(W) -> Unit> = arrayListOf()

    override fun mouseClick(mouseX: Int, mouseY: Int, mouseButton: Int): Boolean {
        if (!isHover(mouseX, mouseY)) return false

        @Suppress("UNCHECKED_CAST")
        clickListeners.forEach { it.invoke(this as W) }

        return true
    }
}
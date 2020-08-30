package net.challenge.client.ui.widget.interaction

import net.challenge.client.ui.events.input.mouse.IGuiMouseClick
import net.challenge.client.ui.widget.IWidget

/**
 * TODO Docs
 */
interface IClickable<T : IWidget> : IGuiMouseClick {

    /**
     * Registered click listeners
     */
    val clickListeners: MutableCollection<(T) -> Unit>

    /**
     * TODO Doc
     */
    fun registerClickHandler(callback: (T) -> Unit) {
        clickListeners.add(callback)
    }
}

package net.challenge.client.ui.widget.renderer

import net.challenge.client.ui.widget.IWidget

/**
 * TODO Doc
 */
interface IWidgetRenderer<W : IWidget> {

    fun render(widget: W, mouseX: Int, mouseY: Int)
}
package net.challenge.client.ui.events.input.mouse

/**
 * TODO Doc
 */
interface IGuiMouseClick {

    /**
     * Called when the mouse is clicked.
     *
     * @param mouseX Mouse X position in pixel.
     * @param mouseY Mouse Y position in pixel.
     * @param mouseButton The button that was clicked on the mouse.
     *
     * @return TODO Doc
     */
    fun mouseClick(mouseX: Int, mouseY: Int, mouseButton: Int): Boolean
}
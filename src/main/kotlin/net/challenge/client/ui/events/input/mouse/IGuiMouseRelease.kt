package net.challenge.client.ui.events.input.mouse

/**
 * TODO Doc
 */
interface IGuiMouseRelease {

    /**
     * Called when a mouse button is released.
     *
     * @param mouseX Mouse X position in pixel.
     * @param mouseY Mouse Y position in pixel.
     * @param mouseButton The button that was clicked on the mouse.
     */
    fun mouseRelease(mouseX: Int, mouseY: Int, mouseButton: Int)
}
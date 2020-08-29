package net.challenge.client.ui.events.input.mouse

/**
 * TODO Doc
 */
interface IGuiMouseClickAndMove {

    /**
     * Called when a mouse button is pressed and the mouse is moved around.
     *
     * @param mouseX Mouse X position in pixel.
     * @param mouseY Mouse Y position in pixel.
     * @param clickedMouseButton The button that was clicked on the mouse.
     * @param timeSinceLastClick The time between now and the last click is in milliseconds.
     */
    fun mouseClickAndMove(mouseX: Int, mouseY: Int, clickedMouseButton: Int, timeSinceLastClick: Long)
}
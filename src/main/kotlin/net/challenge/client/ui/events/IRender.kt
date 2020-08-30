package net.challenge.client.ui.events

/**
 * TODO Doc
 */
interface IRender {

    /**
     * Render the renderer.
     *
     * @param mouseX Mouse X position in pixel.
     * @param mouseY Mouse Y position in pixel.
     */
    fun render(mouseX: Int, mouseY: Int)
}
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
     * @param partialTicks How much time has elapsed since the last tick, in ticks,
     * for use by display rendering routines (range: 0.0 - 1.0).
     * This field is frozen if the display is paused to eliminate jitter.
     */
    fun render(mouseX: Int, mouseY: Int, partialTicks: Float)
}
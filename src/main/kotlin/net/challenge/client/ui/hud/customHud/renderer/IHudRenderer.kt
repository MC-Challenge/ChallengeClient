package net.challenge.client.ui.hud.customHud.renderer

import net.challenge.client.ui.hud.customHud.element.IHudElement

/**
 * # IHudRenderer
 *
 * TODO Doc
 */
interface IHudRenderer {

    /**
     * Collection of all enabled custom-hud-elements
     */
    var enabledElements: Collection<IHudElement>


    /**
     * Render all enabled hud-elements
     *
     * @param mouseX Mouse X position in pixels
     * @param mouseY Mouse Y position in pixels
     * @param partialTicks How much time has elapsed since the last tick, in ticks,
     * for use by display rendering routines (range: 0.0 - 1.0).
     * This field is frozen if the display is paused to eliminate jitter.
     */
    fun renderHudElements(mouseX: Int, mouseY: Int, partialTicks: Float)
}
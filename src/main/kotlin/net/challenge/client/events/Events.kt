package net.challenge.client.events

import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.entity.RenderManager

class RenderPlayerEvent(entity: AbstractClientPlayer, renderManager: RenderManager, x: Double, y: Double, z: Double, partialTicks: Float)

/**
 * Is called when the player presses a key
 *
 * @param key Pressed key
 */
class KeyEvent(val key: Int)

/**
 * # Render2DEvent
 * Called when the screen rendered
 *
 * @param sr Current [ScaledResolution] from minecraft
 * @param partialTicks How much time has elapsed since the last tick, in ticks,
 *                      for use by display rendering routines (range: 0.0 - 1.0).
 *                      This field is frozen if the display is paused to eliminate jitter.
 */
class Render2DEvent(val sr: ScaledResolution, val partialTicks: Float)
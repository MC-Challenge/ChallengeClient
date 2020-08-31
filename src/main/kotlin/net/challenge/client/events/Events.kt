package net.challenge.client.events

import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.client.renderer.entity.RenderManager

class RenderPlayerEvent(entity: AbstractClientPlayer, renderManager: RenderManager, x: Double, y: Double, z: Double, partialTicks: Float)

/**
 * Is called when the player presses a key
 *
 * @param key Pressed key
 */
class KeyEvent(val key: Int)
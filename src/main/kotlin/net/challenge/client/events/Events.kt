package net.challenge.client.events

import me.zero.alpine.event.type.Cancellable
import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.multiplayer.WorldClient
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockPos
import net.minecraft.util.EnumFacing

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

/**
 * Called when the world changes
 *
 * @param worldClient change to this world
 */
class WorldEvent(val worldClient: WorldClient?)

/**
 * Called when a player attack a entity.
 *
 * @param entityPlayer The player who attacks.
 * @param targetEntity The entity that is attacked.
 */
class AttackEntityEvent(val entityPlayer: EntityPlayer, targetEntity: Entity) : Cancellable()